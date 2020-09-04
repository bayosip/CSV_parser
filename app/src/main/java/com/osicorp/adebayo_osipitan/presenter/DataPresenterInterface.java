package com.osicorp.adebayo_osipitan.presenter;

import android.content.BroadcastReceiver;

import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;

import org.json.JSONObject;

import java.util.List;

public interface DataPresenterInterface {

     void retrieveFilterAsJson(JSONObject jsonObject);
     List<Car_Owners_Data> getCarData();
     void retrieveCarDataForDisplay();
     BroadcastReceiver getDownloadReciever();
}
