package com.osicorp.adebayo_osipitan.view.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.osicorp.adebayo_osipitan.R;
import com.osicorp.adebayo_osipitan.model.Car_Owners_Data;
import com.osicorp.adebayo_osipitan.model.Filter;
import com.osicorp.adebayo_osipitan.model.GenUtilities;
import com.osicorp.adebayo_osipitan.presenter.DataPresenterInterface;
import com.osicorp.adebayo_osipitan.presenter.MainPresenter;
import com.osicorp.adebayo_osipitan.view.MainViewInterface;
import com.osicorp.adebayo_osipitan.view.fragments.CarOwnerFragment;
import com.osicorp.adebayo_osipitan.view.fragments.FilterSelectionFragment;
import com.osicorp.adebayo_osipitan.view.fragments.CarListFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    private DataPresenterInterface presenter;
    CarOwnerFragment cOFrag;
    FilterSelectionFragment fSFrag;
    CarListFragment listFrag;
    private TextView title;
    private ContentLoadingProgressBar pb;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupActionBar();
        openListFrag();
    }


    private void init(){
        presenter = new MainPresenter(MainActivity.this);
        listFrag = CarListFragment.getInstance();
        fSFrag = FilterSelectionFragment.getInstance();

        toolbar = findViewById(R.id.appbar_home);
        title = findViewById(R.id.toolbar_title);
        pb = findViewById(R.id.loading_progress);
        pb.hide();
    }

    @Override
    public void showProgress() {
        pb.show();
    }

    private void setupActionBar(){
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();

        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowCustomEnabled(false); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false);
    }

    private void setupOtherActionBar() {
        // Get the ActionBar here to configure the way it behaves.
        setSupportActionBar(toolbar);
        // Get the ActionBar here to configure the way it behaves.
        final ActionBar ab = getSupportActionBar();

//        ab.setDisplayOptions();
        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
    }

    public void openListFrag() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frag_layout, listFrag, "List");
        fragmentTransaction.commitAllowingStateLoss();
        //this.title.setText("iCar");
    }

    public void openFilterFrag(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.frag_layout, fSFrag, "Filter");
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public Activity getViewContext() {
        return MainActivity.this;
    }

    @Override
    public List<Car_Owners_Data> getFullList() {
        return presenter.getCarData();
    }

    @Override
    public void loadCarData() {
        presenter.retrieveCarDataForDisplay();
    }

    @Override
    public void updateListWith(List<Car_Owners_Data> moreData) {
        listFrag.addDataToFullList(moreData);
    }

    @Override
    public void updateListWithFilter(List<Car_Owners_Data> filteredData) {
        listFrag.updateListWithFilteredResults(filteredData);
    }

    @Override
    public void updateListWithSearch(List<Car_Owners_Data> searchedData) {

    }

    @Override
    public void searchListForCarModel(String carModel) {

    }

    @Override
    public void applyAPresetFilter(Filter filter) {

    }

    @Override
    public void showInDetail(int adapterPosition) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(presenter.getDownloadReciever());
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frag_layout);
        if (fragment != null) {
            String tag = fragment.getTag();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!TextUtils.isEmpty(tag) && tag.equals("Filter")) {
                fragmentTransaction.remove(fragment).commitAllowingStateLoss();
            }

        } else {
            GenUtilities.exitApp(MainActivity.this);
        }
    }
}
