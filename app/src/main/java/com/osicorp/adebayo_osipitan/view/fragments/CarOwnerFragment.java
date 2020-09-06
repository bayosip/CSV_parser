package com.osicorp.adebayo_osipitan.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.osicorp.adebayo_osipitan.R;
import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.view.custom_views.ExpandableTextView;

public class CarOwnerFragment extends BaseFragment {

    private TextView fullName, email, country, carMake, Color, year, gender, job_title;
    private ExpandableTextView bio;
    private static final String CAR = "Car";
    private Car_Owners_Data data;

    public CarOwnerFragment getInstance(Car_Owners_Data car_owners_data){
        CarOwnerFragment fragment = new CarOwnerFragment();
        Bundle extra = new Bundle();
        extra.putSerializable(CAR, car_owners_data);
        fragment.setArguments(extra);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter_selection, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        initWidget(view);
    }

    private void initWidget(View view) {
        fullName = view.findViewById(R.id.text);
    }

    private void init() {
        data = (Car_Owners_Data)getArguments().getSerializable(CAR);
    }
}
