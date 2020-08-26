package com.osicorp.adebayo_osipitan.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.osicorp.adebayo_osipitan.R;
import com.osicorp.adebayo_osipitan.view.FragmentListener;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
