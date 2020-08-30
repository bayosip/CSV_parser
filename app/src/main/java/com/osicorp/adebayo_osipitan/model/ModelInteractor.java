package com.osicorp.adebayo_osipitan.model;

import java.util.List;

public interface ModelInteractor {

    void getFilterJsonFile(String url);
    List<String[]> readCsvDataFile();
}
