package com.osicorp.adebayo_osipitan.model;

import java.util.LinkedList;
import java.util.List;

public class Car_Owners_Data {

    int id, car_model_year;
    String first_name, last_name, email, country, car_model, car_colour, gender, job_title, bio;


    public Car_Owners_Data(int id,  String first_name, String last_name, String email,
                           String country, String car_model,int car_model_year, String car_colour,
                           String gender, String job_title, String bio) {
        this.id = id;
        this.car_model_year = car_model_year;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.country = country;
        this.car_model = car_model;
        this.car_colour = car_colour;
        this.gender = gender;
        this.job_title = job_title;
        this.bio = bio;
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

    public List<String> dataAsList(String[] data){
        List<String> carData = new LinkedList<>();

        for (String s: data){
            carData.add(s);
        }
        return carData;
    }
}
