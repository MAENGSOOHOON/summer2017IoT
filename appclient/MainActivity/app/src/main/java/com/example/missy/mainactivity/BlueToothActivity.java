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

    private static final int REQUEST_ENABLE_BT = 3;
    private BluetoothAdapter mBluetoothAdapter = null;
    private Set<BluetoothDevice> mDevice = null;
    private int mPairedDeviceCount = 0;
    private BluetoothDevice mRemotedDevice = null;
    private BluetoothSocket msocket = null;
    OutputStream mOutputStream = null;
    InputStream mInputStream = null;
    String mStrDelimiter = "\n";
    char mCharDelimiter = '\n';
    Thread mWorkerThread = null;
    byte[] readBuffer;
    int readBufferPosition;
    EditText mEditReceive, mEditSend;
    Button mButtonSend;

    protected void onDestroy(){
        try{
            mWorkerThread.interrupt();
            mInputStream.close();
            mOutputStream.close();
            msocket.close();
        }catch (Exception e){
        }
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

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null){
            Toast.makeText(this,"cannot use bluetooth",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"can use bluetooth",Toast.LENGTH_LONG).show();
        }

        if(!mBluetoothAdapter.isEnabled()){
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }

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

    }

    public void onClick(View view){
        sendData(mEditSend.getText().toString());
        mEditSend.setText("");
    }

    void sendData(String msg){
        msg += mStrDelimiter;
        try{
            mOutputStream.write(msg.getBytes());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"send data error",Toast.LENGTH_LONG).show();
            finish();
        }
    }

    void checkBluetooth(){
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(), "cannot use bluetooth", Toast.LENGTH_LONG).show();
            finish();
        }else {
            if(!mBluetoothAdapter.isEnabled()){
                Toast.makeText(getApplicationContext(),"now bluetooth is off", Toast.LENGTH_LONG).show();
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }else selectDevice();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case REQUEST_ENABLE_BT:
                if(resultCode == RESULT_OK){
                    Toast.makeText(this,"success enable",Toast.LENGTH_LONG).show();
                }
                else if(resultCode == RESULT_CANCELED){
                    finish();
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }

    void selectDevice(){
        mDevice = mBluetoothAdapter.getBondedDevices();
        mPairedDeviceCount = mDevice.size();

        if(mPairedDeviceCount == 0){
            Toast.makeText(this,"No device",Toast.LENGTH_LONG);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select device");

        List<String> listItems = new ArrayList<String>();
        for(BluetoothDevice device: mDevice){
          listItems.add(device.getName());{}
        }
        listItems.add("cancle");

        final CharSequence[] items = listItems.toArray(new CharSequence[listItems.size()]);

        builder.setItems(items, new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int item){
                if(item == mPairedDeviceCount){
                    finish();
                }
                else{
                    connectToSelectedDevices(items[item].toString());
                }
            }
        });

        builder.setCancelable(false);
        AlertDialog alert = builder.create();
        alert.show();
    }

    BluetoothDevice getDeviceFromBondedList(String name){
        BluetoothDevice selectedDevice = null;

        for(BluetoothDevice device : mDevice){
            if(name.equals(device.getName())){
                selectedDevice = device;
                break;
            }
        }
        return selectedDevice;
    }

    void connectToSelectedDevices(String selectedDeviceName){
        mRemotedDevice = getDeviceFromBondedList(selectedDeviceName);
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");

                try{
                    msocket = mRemotedDevice.createRfcommSocketToServiceRecord(uuid);
                    msocket.connect();

                    mOutputStream = msocket.getOutputStream();
                    mInputStream = msocket.getInputStream();

                    beginListenForData();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Connet error",Toast.LENGTH_LONG).show();
                    finish();
                }
    }


    void beginListenForData(){
        final Handler handler = new Handler();
        readBufferPosition = 0;
        readBuffer = new byte[1024];

        mWorkerThread = new Thread(new Runnable(){
            public void run(){
                while(!Thread.currentThread().isInterrupted()){
                    try{
                        int bytesAvailable = mInputStream.available();
                        if(bytesAvailable>0){
                            byte[] packetBytes = new byte[bytesAvailable];
                            mInputStream.read(packetBytes);
                            for(int i = 0; i<bytesAvailable; i++){
                                byte b = packetBytes[i];
                                if(b== mCharDelimiter){
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer,0,encodedBytes,0,encodedBytes.length);
                                    final String data = new String(encodedBytes, "utf-8");
                                    readBufferPosition = 0;
                                    handler.post(new Runnable(){
                                        public void run() {
                                            mEditReceive.setText(mEditReceive.getText().toString() + data + mStrDelimiter);
                                        }
                                    });
                                }
                                else{
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }catch (IOException ex){
                        Toast.makeText(getApplicationContext(), "send data error",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });
        mWorkerThread.start();
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
