package com.osicorp.adebayo_osipitan.view.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.osicorp.adebayo_osipitan.view.FragmentListener;

public abstract class BaseFragment extends Fragment {

    protected FragmentListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (FragmentListener)context;
    }
}
