package com.osicorp.adebayo_osipitan.view;

import android.app.Activity;

import com.osicorp.adebayo_osipitan.model.Filter;

public interface FragmentListener {
    Activity getViewContext();
    void applyAPresetFilter(Filter filter);
    void showInDetail(int adapterPosition);
}
