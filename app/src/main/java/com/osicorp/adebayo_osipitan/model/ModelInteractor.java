package com.osicorp.adebayo_osipitan.model;

import org.json.JSONObject;

import java.util.List;

public interface ModelInteractor {

    JSONObject getJSonObjectfromFile(String url);
    List<Car_Owners_Data> readCsvDataFile();
}
