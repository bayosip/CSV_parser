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

public class FullDetailsFragment extends BaseFragment {

    private TextView fullName, email, country, carMake, Color, year, gender, job_title;
    private ExpandableTextView bio;
    private static final String CAR = "Car";
    private Car_Owners_Data data;

    public static FullDetailsFragment getInstance(Car_Owners_Data car_owners_data){
        FullDetailsFragment fragment = new FullDetailsFragment();
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

        fullName = view.findViewById(R.id.textOwnerFN);
        String name = data.getFirst_name() + " " + data.getLast_name();
        fullName.setText(name);
        email = view.findViewById(R.id.textOwnerEmail);
        email.setText(data.getEmail());
        country = view.findViewById(R.id.textOwnerCountry);
        country.setText(data.getCountry());
        carMake = view.findViewById(R.id.textCarModel);
        carMake.setText(data.getCar_model());
        Color = view.findViewById(R.id.textCarColor);
        Color.setText(data.getCar_colour());
        year = view.findViewById(R.id.textCarYear);
        year.setText(String.valueOf(data.getCar_model_year()));
        gender = view.findViewById(R.id.textOwnerGender);
        gender.setText(data.getGender());
        bio = view.findViewById(R.id.textOwnerBio);
        bio.setText(data.getBio());
    }

    private void init() {
        data = (Car_Owners_Data)getArguments().getSerializable(CAR);
    }
}
