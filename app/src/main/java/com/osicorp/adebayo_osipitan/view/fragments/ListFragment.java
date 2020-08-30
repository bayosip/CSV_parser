package com.osicorp.adebayo_osipitan.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.labo.kaji.fragmentanimations.MoveAnimation;
import com.osicorp.adebayo_osipitan.R;
import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.view.list_ui.CarDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment {

    private RecyclerView carData;
    private ImageButton filter;
    private EditText searchBar;
    private List<Car_Owners_Data> fullList, searchedList, filteredList, displayList;
    private CarDataAdapter adapter;

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
        displayList = new ArrayList<>();
        searchedList = new ArrayList<>();
        filteredList = new ArrayList<>();

    }

    private void initWidget(View v){
        carData = v.findViewById(R.id.listCarInfo);
        searchBar = v.findViewById(R.id.editSearch);
        filter = v.findViewById(R.id.buttonFilter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(listener.getViewContext(),
                RecyclerView.VERTICAL, false);
        carData.setLayoutManager(layoutManager);
        adapter = new CarDataAdapter(displayList, listener);

    }

    private void searchForCarModel(String model){
        searchedList.clear();
        for (Car_Owners_Data car: fullList ){
            if(car.getCar_model().equalsIgnoreCase(model)){
                searchedList.add(car);
            }
        }
        displayList.clear();
        displayList.addAll(searchedList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return MoveAnimation.create(MoveAnimation.RIGHT, enter, 500);
    }
}
