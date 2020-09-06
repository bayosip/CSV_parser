package com.osicorp.adebayo_osipitan.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.osicorp.adebayo_osipitan.model.Constants;
import com.osicorp.adebayo_osipitan.model.Filter;
import com.osicorp.adebayo_osipitan.model.FilterDownloadService;
import com.osicorp.adebayo_osipitan.model.GenUtilities;
import com.osicorp.adebayo_osipitan.view.FragmentListener;
import com.osicorp.adebayo_osipitan.view.list_ui.SimpleListAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FilterSelectionFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {


    private static final String TAG = "FilterSelectionFragment";
    private RecyclerView listCountries, listColors;
    private TextView endYear, startYear, gender;
    private List<Filter> filters;
    private Button applyFilter, clear;
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
        Intent intent = new Intent(listener.getViewContext(), FilterDownloadService.class);
        if (!GenUtilities.getAppPref().contains(Constants.FILE_KEY)) {
            listener.getViewContext().startService(intent);
            listener.showProgress();
        }else {
            GenUtilities.message("File Already Downloaded");
            if(filters.size()==0)
                listener.loadFilters();
        }
    }

    private void init(){
        filters = new LinkedList<>();
    }

    private void initWidget(View view){

        filterPicker = view.findViewById(R.id.selectFilterSpinner);
        filterPicker.setOnItemSelectedListener(this);

        listCountries = view.findViewById(R.id.listCountries);
        listColors = view.findViewById(R.id.listColours);

        LinearLayoutManager layoutManager = new LinearLayoutManager(listener.getViewContext(),
                RecyclerView.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(listener.getViewContext(),
                RecyclerView.HORIZONTAL, false);
        listColors.setLayoutManager(layoutManager);
        listCountries.setLayoutManager(layoutManager1);

        endYear = view.findViewById(R.id.textEnd);
        startYear = view.findViewById(R.id.textStart);
        gender = view.findViewById(R.id.textGender);
        applyFilter = view.findViewById(R.id.buttonApplyFilter);
        clear =  view.findViewById(R.id.buttonClearFilter);

        applyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.applyAPresetFilter(selectedFilter);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clearFilter();
            }
        });
    }

    private void displayFilterSetting(Filter filter){
        selectedFilter = filter;
        endYear.setText("" + filter.getEnd_year());
        startYear.setText("" + filter.getStart_year());
        gender.setText(filter.getGender());

        //if (filter.getCountries().length>0)
        {
            SimpleListAdapter adapter1 = new SimpleListAdapter(filter.getCountries(),
                    listener.getViewContext());
            listCountries.swapAdapter(adapter1, true);
        }

        //if (filter.getColors().length>0)
        {
            SimpleListAdapter adapter2 = new SimpleListAdapter(filter.getColors(),
                    listener.getViewContext());
            listColors.swapAdapter(adapter2, true);
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return MoveAnimation.create(MoveAnimation.LEFT, enter, 700);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if(position>0)
            displayFilterSetting(filters.get(position-1));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void updateSpinnerWithFilter(List<Filter> filterList) {
        filters.clear();
        filters = filterList;
        List<String> filterName = new LinkedList<>();
        filterName.add("--Please Select a Filter--");
        for (Filter f : filterList){
            filterName.add( "Filter " + f.getId());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(listener.getViewContext(),
                R.layout.support_simple_spinner_dropdown_item, filterName);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        filterPicker.setAdapter(dataAdapter);
    }
}
