package com.osicorp.adebayo_osipitan.presenter;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.Constants;
import com.osicorp.adebayo_osipitan.model.DataReader;
import com.osicorp.adebayo_osipitan.model.Filter;
import com.osicorp.adebayo_osipitan.model.FilterDownloadService;
import com.osicorp.adebayo_osipitan.model.GenUtilities;
import com.osicorp.adebayo_osipitan.model.ModelInteractor;
import com.osicorp.adebayo_osipitan.view.MainViewInterface;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainPresenter extends BasePresenter<MainViewInterface>  {

    private static final String TAG = "MainPresenter";

    private ModelInteractor interactor;
    private MainViewInterface viewInterface;
    private List<Car_Owners_Data> carData;
    private Filter appliedFilter = null;
    private List<Filter> filters;

    public MainPresenter(MainViewInterface view) {
        super(view);
        viewInterface = view;
        interactor = DataReader.getInstance();
        carData = new LinkedList<>();
        filters = new LinkedList<>();
        view.getViewContext().registerReceiver(downloadReciever, downloadIntentFilter());
    }

    public List<Car_Owners_Data> getCarData() {
        return carData;
    }

    @Override
    public void retrieveFilterAsJson(String uri) {
        viewInterface.hideProgress();
        JSONArray obj = interactor.getJSonObjectfromFile(uri);

        for (int i = 0; i< obj.length(); i++){
            Gson gson = new Gson();
            try {
                Filter aFilter = gson.fromJson(obj.getJSONObject(i).toString(), Filter.class);
                filters.add(aFilter);
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        if(filters.size() > 0){
            viewInterface.updateFilterList(filters);
        }
        //Log.w(TAG, "retrieveFilterAsJson: " +obj.toString() );
    }

    public void retrieveCarDataForDisplay(){
        List<Car_Owners_Data> incomingData = interactor.readCsvDataFile();
        if(incomingData!=null) {
            carData.addAll(incomingData);
            viewInterface.updateDataListWith(incomingData);
        }
    }

    @Override
    public void applyFilterToList(Filter filter) {
        List<Car_Owners_Data> filterData = new ArrayList<>();
        for (Car_Owners_Data data : carData){
            if(doesDataMatchFilter(filter, data)){
                filterData.add(data);
            }
        }
        viewInterface.updateDataListWithFilter(filterData);
    }

    protected boolean doesDataMatchFilter(Filter filter, Car_Owners_Data data){
        Map<String, Object> map= filter.filterMap();
        boolean isFilterMatch = true;
        for(String key: map.keySet()){
            if(!checkEachFilterCategoryForMatch(key, filter, data)){
                return false;
            }
        }
        return isFilterMatch;
    }

    protected boolean checkEachFilterCategoryForMatch(String category_key, Filter filter,
                                                      Car_Owners_Data data){
        String key = category_key;
        boolean isCategoryMatch = false;
        if(key.equalsIgnoreCase(Constants.KEY_START)){
            for (int i = filter.getStart_year(); i<= filter.getEnd_year(); i++){
                if(data.getCar_model_year() == i){
                    isCategoryMatch =true;
                    break;
                }
            }
        }else if(key.equalsIgnoreCase(Constants.KEY_GENDER)){
            if(data.getGender().equalsIgnoreCase(filter.getGender())){
                isCategoryMatch = true;
            }
        }else if(key.equalsIgnoreCase(Constants.KEY_COLORS)){
            for (String color: filter.getColors()){
                if (color.equalsIgnoreCase(data.getCar_colour())){
                    isCategoryMatch = true;
                    break;
                }
            }
        }else if(key.equalsIgnoreCase(Constants.KEY_COUNTRIES)){
            for (String country: filter.getCountries()){
                if (country.equalsIgnoreCase(data.getCountry())){
                    isCategoryMatch = true;
                    break;
                }
            }
        }

        return isCategoryMatch;
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
                    viewInterface.hideProgress();
                    Log.w(TAG, "onReceive: " + "BroadCast Received" );
                    Bundle bundle = intent.getExtras();
                    if (bundle != null) {
                        String uri = bundle.getString(Constants.FILE_KEY);
                        if(!GenUtilities.getAppPref().contains(Constants.FILE_KEY)){
                            if(!TextUtils.isEmpty(uri)){
                                GenUtilities.getAppPref().edit().putString(Constants.FILE_KEY, uri)
                                        .apply();
                            }
                        }
                        int resultCode = bundle.getInt(FilterDownloadService.RESULT);
                        if (resultCode == Activity.RESULT_OK) {
                            GenUtilities.message(
                                    "Download complete. Download URI: " + uri);
                            retrieveFilterAsJson(uri);

                        } else {
                            GenUtilities.message( "Download failed");
                        }
                    }
                    break;
            }
        }
    };
}
