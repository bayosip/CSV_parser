package com.osicorp.adebayo_osipitan.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {

    int id, start_year, end_year;
    String gender;
    String[] countries, colors;


    public Filter(int id, int start_year, int end_year, String gender,
                  String[] countries, String[] colors) {
        this.id = id;
        this.start_year = start_year;
        this.end_year = end_year;
        this.gender = gender;
        this.countries = countries;
        this.colors = colors;
    }

    public int getId() {
        return id;
    }

    public int getStart_year() {
        return start_year;
    }

    public int getEnd_year() {
        return end_year;
    }

    public String getGender() {
        return gender;
    }

    public String[] getCountries() {
        return countries;
    }

    public String[] getColors() {
        return colors;
    }

    public Map<String, Object> filterMap(){
        Map<String, Object> map = new HashMap<>();

        map.put(Constants.KEY_ID, this.id);
        map.put(Constants.KEY_START, this.start_year);
        map.put(Constants.KEY_END, this.end_year);
        map.put(Constants.KEY_GENDER, this.gender);
        map.put(Constants.KEY_COUNTRIES, this.countries);
        map.put(Constants.KEY_COLORS, this.colors);

        return map;
    }
}
