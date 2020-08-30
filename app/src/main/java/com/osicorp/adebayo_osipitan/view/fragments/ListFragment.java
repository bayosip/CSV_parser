package com.osicorp.adebayo_osipitan.view.fragments;

import android.view.animation.Animation;

import com.labo.kaji.fragmentanimations.MoveAnimation;

public class ListFragment extends BaseFragment {



    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return MoveAnimation.create(MoveAnimation.RIGHT, enter, 500);
    }
}
