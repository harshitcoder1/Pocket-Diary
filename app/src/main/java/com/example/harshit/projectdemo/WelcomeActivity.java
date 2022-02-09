package com.example.harshit.projectdemo;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {

    private final static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            Intent intent=new Intent(WelcomeActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
            }
        },SPLASH_TIME_OUT);
    }
}
