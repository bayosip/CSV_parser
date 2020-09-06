package com.osicorp.adebayo_osipitan.view;

import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.Filter;

import java.util.List;

public interface MainViewInterface extends FragmentListener {

    void updateDataListWith(List<Car_Owners_Data> moreData);
    void updateDataListWithFilter(List<Car_Owners_Data> filteredData);
    void updateListWithSearch(List<Car_Owners_Data> searchedData);
    void updateFilterList(List<Filter> filters);
    void hideProgress();

}
