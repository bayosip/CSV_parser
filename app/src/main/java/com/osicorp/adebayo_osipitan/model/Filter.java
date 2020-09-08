package com.osicorp.adebayo_osipitan.model;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter implements Serializable {
    private static final String TAG = "Filter";

    @SerializedName("id")
    int id;
    @SerializedName("start_year")
    int start_year;
    @SerializedName("end_year")
    int end_year;
    @SerializedName("gender")
    String gender;
    @SerializedName("countries")
    String[] countries;
    @SerializedName("colors")
    String[] colors;


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


    public boolean checkEachFilterCategoryForMatch(Car_Owners_Data data){
        Log.w(TAG, "-- new Car --> id: " + data.getId() );
        boolean isCategoryMatch = false;

        //check If car model year is in range

        for (int i = this.start_year; i<= this.end_year; i++) {
            if (data.getCar_model_year() == i) {
                isCategoryMatch = true;
                break;
            }
        }
        Log.w(TAG, "checkEachFilterCategoryForMatch: Year is - " + isCategoryMatch);
        // if yes
        if (isCategoryMatch) {
            //check for same gender or no gender specified
            if (!TextUtils.isEmpty(this.gender)) {
                if (data.getGender().equalsIgnoreCase(this.gender)) {
                    isCategoryMatch = true;
                } else isCategoryMatch = false;
            } else isCategoryMatch = true;

            Log.w(TAG, "checkEachFilterCategoryForMatch: " + Constants.KEY_GENDER +" is - " + isCategoryMatch);
            //if yes for above
            if (isCategoryMatch){
                //check if car colour is in range
                if (this.colors != null) {
                    for (String color : this.colors) {
                        if (color.equalsIgnoreCase(data.getCar_colour())) {
                            isCategoryMatch = true;
                            break;
                        }else isCategoryMatch = false;
                    }
                }else isCategoryMatch = true;

                Log.w(TAG, "checkEachFilterCategoryForMatch: " + Constants.KEY_COLORS +" is - " + isCategoryMatch);
                //if yes
                if(isCategoryMatch){
                    if(this.countries!=null) {
                        //check if car country is in range
                        for (String country : this.countries) {
                            if (country.equalsIgnoreCase(data.getCountry())) {
                                isCategoryMatch = true;
                                break;
                            } else isCategoryMatch = false;
                        }
                    }else isCategoryMatch = true;
                    Log.w(TAG, "checkEachFilterCategoryForMatch: " + Constants.KEY_COUNTRIES +" is - " + isCategoryMatch);
                }
            }
        }



        return isCategoryMatch;
    }

    public Map<String, Object> filterMap(){
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.KEY_START, this.start_year);
        map.put(Constants.KEY_END, this.end_year);
        map.put(Constants.KEY_GENDER, this.gender);
        map.put(Constants.KEY_COUNTRIES, this.countries);
        map.put(Constants.KEY_COLORS, this.colors);
        return map;
    }
}
