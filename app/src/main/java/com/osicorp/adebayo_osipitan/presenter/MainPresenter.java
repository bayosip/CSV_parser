package com.osicorp.adebayo_osipitan.presenter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.Constants;
import com.osicorp.adebayo_osipitan.model.DataDownloads;
import com.osicorp.adebayo_osipitan.model.FilterDownloadService;
import com.osicorp.adebayo_osipitan.model.GenUtilities;
import com.osicorp.adebayo_osipitan.model.ModelInteractor;
import com.osicorp.adebayo_osipitan.view.MainViewInterface;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainViewInterface>  {

    private static final String TAG = "MainPresenter";

    private ModelInteractor interactor;
    private MainViewInterface viewInterface;
    private List<Car_Owners_Data> carData;
    public MainPresenter(MainViewInterface view) {
        super(view);
        viewInterface = view;
        interactor = DataDownloads.getInstance();
        carData = new LinkedList<>();
        view.getViewContext().registerReceiver(downloadReciever, downloadIntentFilter());
    }

    public List<Car_Owners_Data> getCarData() {
        return carData;
    }

    @Override
    public void retrieveFilterAsJson(JSONObject jsonObject) {

    }

    public void retrieveCarDataForDisplay(){
        List<Car_Owners_Data> incomingData = interactor.readCsvDataFile();
        carData.addAll(incomingData);
        viewInterface.updateListWith(incomingData);
    }

     private IntentFilter downloadIntentFilter() {
        IntentFilter intentFilter =new IntentFilter();
        intentFilter.addAction(Constants.DOWNLOAD_FINISHED);
        return intentFilter;
    }

    public BroadcastReceiver getDownloadReciever() {
        return downloadReciever;
    }

    private final BroadcastReceiver downloadReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            switch (action) {
                case Constants
                        .DOWNLOAD_FINISHED:
                    Log.w(TAG, "onReceive: " + "BroadCast Received" );
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        String string = bundle.getString(Constants.FILE_KEY);
                        int resultCode = bundle.getInt(FilterDownloadService.RESULT);
                        if (resultCode == Activity.RESULT_OK) {
                            GenUtilities.message(
                                    "Download complete. Download URI: " + string);

                        } else {
                            GenUtilities.message( "Download failed");
                        }
                    }
                    break;
            }
        }
    };
}
