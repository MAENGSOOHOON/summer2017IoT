package com.example.missy.environmentview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void airclick(View v){
        Intent air_intent = new Intent(this, AirActivity.class);
        startActivity(air_intent);
    }

    public void heartclick(View v){
        Intent heart_intent = new Intent(this, HeartActivity.class);
        startActivity(heart_intent);
    }

    public void graphsclick(View v){
        Intent graphsclick_intent = new Intent(this, GraphsActivity.class);
        startActivity(graphsclick_intent);
    }

    public void mapclick(View v){
        Intent map_intent = new Intent(this, MapActivity.class);
        startActivity(map_intent);
    }
}
