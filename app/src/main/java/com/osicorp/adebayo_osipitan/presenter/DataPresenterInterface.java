package com.osicorp.adebayo_osipitan.presenter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;

import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.Filter;
import java.util.List;

public interface DataPresenterInterface {

     void retrieveFilterAsJson(String uri);
     List<Car_Owners_Data> getCarData();
     void retrieveCarDataForDisplay();
     Intent registerBCReceiver(Activity activity);
     BroadcastReceiver getBroadCastReceiver();

    void applyFilterToList(Filter filter);

    void clearAllFiltersorSearch();
}
