package com.osicorp.adebayo_osipitan.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.labo.kaji.fragmentanimations.MoveAnimation;
import com.osicorp.adebayo_osipitan.R;
import com.osicorp.adebayo_osipitan.model.Filter;
import com.osicorp.adebayo_osipitan.view.FragmentListener;
import com.osicorp.adebayo_osipitan.view.list_ui.SimpleListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FilterSelectionFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {


    private RecyclerView listCountries, listColors;
    private TextView endYear, startYear, gender;
    private List<Filter> filters;
    private Button applyFilter;
    private Spinner filterPicker;
    private Filter selectedFilter;


    private FilterSelectionFragment(){}

    public static FilterSelectionFragment getInstance(){
        FilterSelectionFragment fragment =  new FilterSelectionFragment();
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

    private void init(){
        filters = new ArrayList<>();
    }

    private void initWidget(View view){

        filterPicker = view.findViewById(R.id.selectFilterSpinner);
        filterPicker.setOnItemSelectedListener(this);

        // Creating adapter for spinner
        ArrayAdapter<Filter> dataAdapter = new ArrayAdapter<>(listener.getViewContext(),
                R.layout.support_simple_spinner_dropdown_item, filters);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        filterPicker.setAdapter(dataAdapter);

        listCountries = view.findViewById(R.id.listCountries);
        listColors = view.findViewById(R.id.listColours);

        LinearLayoutManager layoutManager = new LinearLayoutManager(listener.getViewContext(),
                RecyclerView.HORIZONTAL, false);
        listColors.setLayoutManager(layoutManager);
        listCountries.setLayoutManager(layoutManager);

        endYear = view.findViewById(R.id.textEnd);
        startYear = view.findViewById(R.id.textStart);
        gender = view.findViewById(R.id.textGender);
        applyFilter = view.findViewById(R.id.buttonApplyFilter);
        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.applyAPresetFilter(selectedFilter);
            }
        });
    }

    private void displayFilterSetting(Filter filter){
        selectedFilter = filter;
        endYear.setText("" + filter.getEnd_year());
        startYear.setText("" + filter.getStart_year());
        gender.setText(filter.getGender());

        if (filter.getCountries().length>0){
            SimpleListAdapter adapter1 = new SimpleListAdapter(filter.getCountries(),
                    listener.getViewContext());
            listCountries.setAdapter(adapter1);
        }

        if (filter.getColors().length>0){
            SimpleListAdapter adapter2 = new SimpleListAdapter(filter.getColors(),
                    listener.getViewContext());
            listColors.setAdapter(adapter2);
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return MoveAnimation.create(MoveAnimation.LEFT, enter, 700);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        displayFilterSetting(filters.get(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
