package com.osicorp.adebayo_osipitan.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.osicorp.adebayo_osipitan.R;

public class SplashView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);
        if (getIntent().getExtras() != null && getIntent().getExtras()
                .getBoolean("EXIT", false)) {
            if(Build.VERSION.SDK_INT >= 21)finishAndRemoveTask();
            else finish();
        }
    }
}
