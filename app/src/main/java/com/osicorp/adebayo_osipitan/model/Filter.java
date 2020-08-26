package com.osicorp.adebayo_osipitan.model;

import java.util.List;

public class Filter {

    int id, start_year, end_year;
    String gender;
    String[] countries, colors;


    public Filter(int id, int start_year, int end_year, String gender, String[] countries, String[] colors) {
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
}
