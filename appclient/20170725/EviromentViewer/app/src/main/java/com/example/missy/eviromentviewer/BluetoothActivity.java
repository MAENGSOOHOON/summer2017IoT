package com.example.missy.eviromentviewer;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class BluetoothActivity extends AppCompatActivity implements View.OnClickListener{


    private Button mSendButton;
    public static String EXTRA_DEVICE_ADDRESS = "device_address";

    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_CONNECT_DEVICE_INSECURE = 2;
    private static final int REQUEST_ENABLE_BT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            BluetoothChatFragment fragment = new BluetoothChatFragment();
            transaction.replace(R.id.content_Fragment, fragment);
            transaction.commit();
        }

        mSendButton = (Button) findViewById(R.id.search);
        mSendButton.setOnClickListener(this);
    }

    public void onClick(View v){
        Intent serverIntent = new Intent(this,DeviceListActivity.class);
        startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
        String device_name = serverIntent.getStringExtra(EXTRA_DEVICE_ADDRESS);
        Toast.makeText(this,device_name,Toast.LENGTH_LONG).show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bluetooth_chat, menu);
        return true;
    }

}
