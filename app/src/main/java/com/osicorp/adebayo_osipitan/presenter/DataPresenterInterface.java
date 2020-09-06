package com.osicorp.adebayo_osipitan.presenter;

import android.content.BroadcastReceiver;
import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.Filter;
import java.util.List;

public interface DataPresenterInterface {

     void retrieveFilterAsJson(String uri);
     List<Car_Owners_Data> getCarData();
     void retrieveCarDataForDisplay();
     BroadcastReceiver getDownloadReciever();

    void applyFilterToList(Filter filter);
}
