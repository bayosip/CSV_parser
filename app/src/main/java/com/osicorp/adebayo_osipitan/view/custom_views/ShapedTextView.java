package com.osicorp.adebayo_osipitan.view.custom_views;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.osicorp.adebayo_osipitan.R;

public class ShapedTextView extends AppCompatTextView {

    public ShapedTextView(@NonNull Context context) {
        super(context);
    }

    @Override
    public void setBackgroundResource(int resId) {
        super.setBackgroundResource(R.drawable.curved_view);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(Color.WHITE);
    }
}
