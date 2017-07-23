package com.example.missy.qiproject;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static com.example.missy.qiproject.R.styleable.View;

public class RTViewer extends AppCompatActivity {

    DrawerLayout select_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rtviewer);
        select_menu = (DrawerLayout) findViewById(R.id.option_view);
        select_menu.closeDrawers();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setSlidingMenu();
    }

    pirvate void setSelect_menu(){

    }
}
