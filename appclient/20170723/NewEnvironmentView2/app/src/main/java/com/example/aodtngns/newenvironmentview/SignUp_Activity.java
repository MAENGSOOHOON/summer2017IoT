package com.example.aodtngns.newenvironmentview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignUp_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);
    }

    public void signupClick(View v) {
        Intent signupIntent = new Intent(this, SignUp_Activity.class);
        startActivity(signupIntent);
    }
}
