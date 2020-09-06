package com.osicorp.adebayo_osipitan.model;

import org.json.JSONArray;

import java.util.List;

public interface ModelInteractor {

    JSONArray getJSonObjectfromFile(String url);
    List<Car_Owners_Data> readCsvDataFile();
}
