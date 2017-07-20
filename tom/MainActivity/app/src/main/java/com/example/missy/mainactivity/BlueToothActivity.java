package com.example.missy.mainactivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.GenericArrayType;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.logging.SocketHandler;

public class BlueToothActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static final int REQUEST_ENABLE_BT = 10;
    int mPairedDeviceCount = 0;
    Set<BluetoothDevice> mDevices;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothDevice mRemoteDevice;
    BluetoothSocket mSocket = null;
    OutputStream mOutputStream = null;
    InputStream mInputStream = null;
    String mStrDelimiter = "\n";
    char mCharDelimiter = '\n';
    Thread mWorkerThread = null;
    byte[] readBuffer;
    int readBufferPosition;
    EditText mEditReceive, mEditSend;
    Button mButtonSend;


    @Override
    protected void onDestroy() { //어플리케이션이 종료될때  호출되는 함수
        try{
            mWorkerThread.interrupt(); // 데이터 수신 쓰레드 종료
            mInputStream.close();
            mOutputStream.close();
            mSocket.close();
        }catch(Exception e){}
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mEditReceive = (EditText)findViewById(R.id.receiveString);
        mEditSend = (EditText)findViewById(R.id.sendString);
        mButtonSend = (Button)findViewById(R.id.sendButton);
        mButtonSend.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view)
            {
                sendData(mEditSend.getText().toString());
                mEditSend.setText("");
            }
        });
        checkBluetooth();
    }

    BluetoothDevice getDeviceFromBondedList(String name){ //해당 블루투스 장치 객체를 페어링 된 장치 목록에서 찾아내기
        BluetoothDevice selectedDevice = null;
        for (BluetoothDevice device : mDevices)
        {
            if(name.equals(device.getName()))
            {
                selectedDevice = device;
                break;
            }
        }
        return selectedDevice;
    }

    void sendData(String msg){
        msg += mStrDelimiter; // 문자열 종료 표시
        try{
            mOutputStream.write(msg.getBytes()); // 문자열 전송
        }catch(Exception e){
// 문자열 전송 도중 오류가 발생한 경우
            Toast.makeText(getApplicationContext(),"데이터 전송 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    void connectToSelectedDevice(String selectedDeviceName) { //소켓
        mRemoteDevice = getDeviceFromBondedList(selectedDeviceName);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
        Log.v("오류", "ㅁㄴㅇ린ㅇ1");
/*
        if(mRemoteDevice.getBondState()==mRemoteDevice.BOND_BONDED)
        {
            Log.d(mRemoteDevice.getName());
        }
        */
        try {
// 소켓 생성
            mSocket = mRemoteDevice.createInsecureRfcommSocketToServiceRecord(uuid);
           // Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        } catch (IOException e1) {
            Log.v("socket not created", "gg");
            e1.printStackTrace();
        }
        try {
            mSocket.connect();
        } catch (IOException e) {
            try {
                mSocket.close();
                Log.v("ff", "Cannot connect");
            } catch (IOException e1) {
                Log.v("hh", "Socket not closed");
                e1.printStackTrace();
            }
        }
    }
        /*
// RFCOMM 채널을 통한 연결
            mSocket.connect();
            Log.v("rfcomm채널 연결","why?");
// 데이터 송수신을 위한 스트림 얻기
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
            Log.v("데이터 송수신을 위한 스트림","why?");
// 데이터 수신 준비
            beginListenForData();
            Log.v("데이터 수신 준비","why?");
        }catch(Exception e){
// 블루투스 연결 중 오류 발생
            Toast.makeText(getApplicationContext(),"블루투스 연결 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
            Log.v("블루투스 연결 중 오류","why?");
            finish(); // 어플리케이션 종료
        }
    }
*/
    void beginListenForData(){ //데이터 수신
        final Handler handler = new Handler();

        readBufferPosition = 0; // 버퍼 내 수신 문자 저장 위치
        readBuffer = new byte[1024]; // 수신 버퍼

// 문자열 수신 쓰레드
        mWorkerThread = new Thread(new Runnable(){
            public void run(){
                while(!Thread.currentThread().isInterrupted()){
                    try {
                        int bytesAvailable = mInputStream.available(); // 수신 데이터 확인
                        if(bytesAvailable > 0){
                            byte[] packetBytes = new byte[bytesAvailable];
                            mInputStream.read(packetBytes);
                            for(int i = 0; i < bytesAvailable; i++){
                                byte b = packetBytes[i];
                                if(b == mCharDelimiter){
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0,encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "utf-8");
                                    readBufferPosition = 0;
                                    handler.post(new Runnable(){
                                        public void run(){
// 수신된 문자열 데이터에 대한 처리 작업
                                            mEditReceive.setText(mEditReceive.getText().toString()+ data + mStrDelimiter);
                                        }
                                    });
                                }
                                else{
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex){
// 데이터 수신 중 오류 발생
                        Toast.makeText(getApplicationContext(),"데이터 수신 중 오류가 발생했습니다.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });
        mWorkerThread.start();
    }
    void selectDevice(){ //AkertDialog 이용하여 페어링 된 장치목록 보여주기
        /*2. BluetoothAdapter 초기화.
        BluetoothAdapter는 연결, 페어링, 검색 등 Bluetooth 연동을 위한 기본 클래스입니다.
        초기화 했을 때 인스턴스가 null 이면 Bluetooth를 지원하지 않는 핸드폰으로 연동이 불가능 합니다
*/
        mDevices = mBluetoothAdapter.getBondedDevices();
        mPairedDeviceCount = mDevices.size();
        if(mPairedDeviceCount == 0){
// 페어링 된 장치가 없는 경우
            Toast.makeText(getApplicationContext(),"페어링된 장치가 없습니다.", Toast.LENGTH_LONG).show();
            finish();// 어플리케이션 종료
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("블루투스 장치 선택");
// 페어링 된 블루투스 장치의 이름 목록 작성

        List<String> listItems = new ArrayList<String>();
        for (BluetoothDevice device : mDevices) {
            listItems.add(device.getName());
        }
        listItems.add("취소");// 취소 항목 추가

        final CharSequence[] items =listItems.toArray(new CharSequence[listItems.size()]);

        builder.setItems(items, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int item){
                if(item == mPairedDeviceCount){
// 연결할 장치를 선택하지 않고 ‘취소’를 누른 경우
                    Toast.makeText(getApplicationContext(),"연결할 장치를 선택하지 않았습니다.", Toast.LENGTH_LONG).show();
                    finish();
                }else{
// 연결할 장치를 선택한 경우
// 선택한 장치와 연결을 시도함
                    Log.v("connect오sdfasfaf류","ㅁㄴㅇ린ㅇ1");
                    connectToSelectedDevice(items[item].toString());
                }
            }
        });
        builder.setCancelable(false); // 뒤로 가기 버튼 사용 금지
        AlertDialog alert = builder.create();
        alert.show();
    }

    void checkBluetooth(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
// 장치가 블루투스를 지원하지 않는 경우
            Toast.makeText(getApplicationContext(),"기기가 블루투스를 지원하지 않습니다.", Toast.LENGTH_LONG).show();
            finish();// 어플리케이션 종료
        }else {
// 장치가 블루투스를 지원하는 경우//3. 핸드폰에 Bluetooth 설정 켜기.
            if (!mBluetoothAdapter.isEnabled()) {
// 블루투스를 지원하지만 비활성 상태인 경우
// 블루투스를 활성 상태로 바꾸기 위해 사용자 동의 요청
                Toast.makeText(getApplicationContext(),"현재 블루투스가 비활성 상태입니다.", Toast.LENGTH_LONG).show();
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }else selectDevice();
// 블루투스를 지원하며 활성 상태인 경우
// 페어링 된 기기 목록을 보여주고 연결할 장치를 선택
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case REQUEST_ENABLE_BT:
                if(resultCode == RESULT_OK){
// 블루투스가 활성 상태로 변경됨
                    selectDevice();
                }else if(resultCode == RESULT_CANCELED){
// 블루투스가 비활성 상태임
                    Toast.makeText(getApplicationContext(),"블루투스를 사용할 수 없어 프로그램을 종료합니다.",Toast.LENGTH_LONG).show();
                    finish();// 어플리케이션 종료
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.blue_tooth, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
