package com.example.missy.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_pm) {
            Intent mov_pm = new Intent(this,PMActivity.class);
            startActivity(mov_pm);
        } else if (id == R.id.nav_health) {
            Intent mov_hel = new Intent(this, HealthActivity.class);
            startActivity(mov_hel);
        } else if (id == R.id.nav_air) {
            Intent mov_air = new Intent(this, AirActivity.class);
            startActivity(mov_air);
        } else if (id == R.id.nav_now) {
            Intent mov_new = new Intent(this, NowActivity.class);
            startActivity(mov_new);
        } else if (id == R.id.nav_History) {
            Intent mov_his = new Intent(this, HistoryActivity.class);
            startActivity(mov_his);
        } else if(id == R.id.nav_blue){
            Intent mov_bl = new Intent(this, BlueToothActivity.class);
            startActivity(mov_bl);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
