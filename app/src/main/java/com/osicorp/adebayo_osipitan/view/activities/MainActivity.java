package com.osicorp.adebayo_osipitan.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import com.osicorp.adebayo_osipitan.R;
import com.osicorp.adebayo_osipitan.model.Filter;
import com.osicorp.adebayo_osipitan.presenter.DataPresenterInterface;
import com.osicorp.adebayo_osipitan.presenter.MainPresenter;
import com.osicorp.adebayo_osipitan.view.FragmentListener;
import com.osicorp.adebayo_osipitan.view.MainViewInterface;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private DataPresenterInterface presenterInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void init(){
        presenterInterface = new MainPresenter(MainActivity.this);
    }

    @Override
    public Activity getViewContext() {
        return MainActivity.this;
    }

    @Override
    public void applyAPresetFilter(Filter filter) {

    }

    @Override
    public void showInDetail(int adapterPosition) {

    }
}
