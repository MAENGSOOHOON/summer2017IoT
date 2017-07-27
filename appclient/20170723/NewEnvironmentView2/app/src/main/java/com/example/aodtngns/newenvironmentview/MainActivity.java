package com.example.aodtngns.newenvironmentview;

import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pmClick(View v){
        Intent nowIntent = new Intent(this, Pm_Activity.class);
        startActivity(nowIntent);
    }

    public void airClick(View v){
        Intent airIntent = new Intent(this, Air_Activity.class);
        startActivity(airIntent);
    }

    public void heClick(View v){
        Intent heIntent = new Intent(this, Health_Activity.class);
        startActivity(heIntent);
    }

    public void searchClick(View v){
        Intent searchIntent = new Intent(this, Search_Activity.class);
        startActivity(searchIntent);
    }

    public void nowClick(View v){
        Intent nowIntent = new Intent(this, Now_Activity.class);
        startActivity(nowIntent);
    }

    public void hisClick(View v){
        Intent hisIntent = new Intent(this, History_Activity.class);
        startActivity(hisIntent);

    }
    public void loginClick(View v) {
        Intent loginIntent = new Intent(this, LogIn_Activity.class);
        startActivity(loginIntent);
    }
}
