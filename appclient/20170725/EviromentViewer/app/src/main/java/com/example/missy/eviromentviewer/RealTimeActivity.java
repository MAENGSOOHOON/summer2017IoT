package com.example.missy.eviromentviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class RealTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time);
    }
    public void pmClick(View v){
        Intent pmIntent = new Intent(this, PMActivity.class);
        startActivity(pmIntent);
    }

    public void heClick(View v){
        Intent helIntent = new Intent(this, HealthActivity.class);
        startActivity(helIntent);
    }

    public void airClick(View v){
        Intent airIntent = new Intent(this, AirActivity.class);
        startActivity(airIntent);
    }

    public void hisClick(View v){
        Intent hisIntent = new Intent(this, HistoryActivity.class);
        startActivity(hisIntent);
    }

    public void nowClick(View v){
        Intent nowIntent = new Intent(this, NowActivity.class);
        startActivity(nowIntent);
    }
}
