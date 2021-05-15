package com.geekyelement.duo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private boolean IsLogged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("tokenFile", Context.MODE_PRIVATE);
                IsLogged = preferences.getBoolean("LoginStatus",false);
                Intent i;
                if(IsLogged) {
                    i = new Intent(MainActivity.this, Home.class);

                    startActivity(i);
                }
                else {
                    i = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        },3000);
    }
}
