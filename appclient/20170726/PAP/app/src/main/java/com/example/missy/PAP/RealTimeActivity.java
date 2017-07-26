package com.example.missy.PAP;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RealTimeActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);
    }

    public void pmClick(View v){
        Intent pmIntent = new Intent(this, PmAqiActivity.class);
        startActivity(pmIntent);
    }

    public void heClick(View v){
        Intent helIntent = new Intent(this, HealthActivity.class);
        startActivity(helIntent);
    }


    public void hisClick(View v){
        Intent hisIntent = new Intent(this, HistoryActivity.class);
        startActivity(hisIntent);
    }

    public void nowClick(View v){
        Intent nowIntent = new Intent(this, CurrentActivity.class);
        startActivity(nowIntent);
    }
    public void btClick(View v) {
        Intent BTintent = new Intent(this, BluetoothActivity.class);
        startActivity(BTintent);
    }

    public void mpClick(View v) {
        Intent now_mapintent = new Intent(this, CurrentActivity.class);
        startActivity(now_mapintent);
    }
    public void searchmapClick(View v) {
        Intent now_mapintent = new Intent(this, CurrentActivity.class);
        startActivity(now_mapintent);
    }
    public void air_navClick(View v) {
        Intent airintent = new Intent(this, AirActivity.class);
        startActivity(airintent);
    }

    public void historyClick(View v) {
        Intent historyintent = new Intent(this, HistoryActivity.class);
        startActivity(historyintent);
    }
    public void airClick(View v){
        Intent airIntent = new Intent(this, AirActivity.class);
        startActivity(airIntent);
    }
    public void loginClick(View v){
        Intent loginIntent = new Intent(this, LogInActivity.class);
        startActivity(loginIntent);
    }

    public void searchClick(View v){
        Intent SearchClick = new Intent(this, SearchActivity.class);
        startActivity(SearchClick);
    }

}
