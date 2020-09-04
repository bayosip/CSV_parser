package com.osicorp.adebayo_osipitan.view;

import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;

import java.util.List;

public interface MainViewInterface extends FragmentListener {

    void updateListWith(List<Car_Owners_Data> moreData);
    void updateListWithFilter(List<Car_Owners_Data> filteredData);
    void updateListWithSearch(List<Car_Owners_Data> searchedData);
}
