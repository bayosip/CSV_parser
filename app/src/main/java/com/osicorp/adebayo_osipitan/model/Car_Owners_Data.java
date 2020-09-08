package com.osicorp.adebayo_osipitan.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Car_Owners_Data implements Serializable {

    private int id, car_model_year;
    private String first_name, last_name, email, country, car_model, car_colour, gender, job_title, bio;
    private String[] data;

    public Car_Owners_Data(String[] csv) {
        this.data = csv;
        this.id = Integer.valueOf(csv[0]);
        this.first_name = csv[1];
        this.last_name = csv[2];
        this.email = csv[3];
        this.country = csv[4];
        this.car_model = csv[5];
        this.car_model_year = Integer.valueOf(csv[6]);
        this.car_colour = csv[7];
        this.gender = csv[8];
        this.job_title = csv[9];
        this.bio = csv[10];
    }

    public int getId() {
        return id;
    }

    public int getCar_model_year() {
        return car_model_year;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getCar_model() {
        return car_model;
    }

    public String getCar_colour() {
        return car_colour;
    }

    public String getGender() {
        return gender;
    }

    public String getJob_title() {
        return job_title;
    }

    public String getBio() {
        return bio;
    }

    public List<String> dataAsList(){
        List<String> carData = new LinkedList<>();

        for (String s: data){
            carData.add(s);
        }
        return carData;
    }
}
