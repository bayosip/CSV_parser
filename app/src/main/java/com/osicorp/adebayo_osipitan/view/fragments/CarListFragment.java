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

public class CarListFragment extends BaseFragment {

    private RecyclerView carData;
    private ImageButton filter;
    private EditText searchBar;
    private List<Car_Owners_Data>  displayList;
    private CarDataAdapter adapter;
    private Boolean reachedBottom = false;

    public static CarListFragment getInstance(){
        return new CarListFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init();
        initWidget(view);
        listener.loadCarData();
    }

    private void init(){
        displayList = new ArrayList<>();
        displayList.clear();

    }

    private void initWidget(View v){

        carData = v.findViewById(R.id.listCarInfo);
        searchBar = v.findViewById(R.id.editSearch);
        filter = v.findViewById(R.id.buttonFilter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(listener.getViewContext(),
                RecyclerView.VERTICAL, false);
        carData.setLayoutManager(layoutManager);
        adapter = new CarDataAdapter(displayList, listener);
        carData.setAdapter(adapter);
        carData.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                reachedBottom = !recyclerView.canScrollVertically(1);
                if (reachedBottom) {
                    listener.loadCarData();
                }
            }
        });
    }

    public void revertToOriginalList(List<Car_Owners_Data> data){
        displayList.clear();
        displayList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    public void addDataToFullList(List<Car_Owners_Data> moreData){
        displayList.addAll(moreData);
        adapter.notifyItemChanged(0);
    }

    public void updateListWithFilteredResults(List<Car_Owners_Data> result){
        displayList.clear();
        displayList.addAll(result);
        adapter.notifyDataSetChanged();
    }

    private void searchForCarModel(String model){
        displayList.clear();
        adapter.notifyDataSetChanged();
        for (Car_Owners_Data car: listener.getFullList() ){
            if(car.getCar_model().equalsIgnoreCase(model)){
                displayList.add(car);
                adapter.notifyItemChanged(0);
            }
        }
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return MoveAnimation.create(MoveAnimation.RIGHT, enter, 500);
    }
}
