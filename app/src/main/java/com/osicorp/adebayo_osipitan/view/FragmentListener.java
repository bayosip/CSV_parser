package com.osicorp.adebayo_osipitan.view;

import android.app.Activity;

import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.Filter;

import java.util.List;

public interface FragmentListener {
    Activity getViewContext();
    List<Car_Owners_Data> getFullList();
    void searchListForCarModel(String carModel);
    void applyAPresetFilter(Filter filter);
    void showInDetail(int adapterPosition);

    void loadCarData();

    void showProgress();
}
