package com.osicorp.adebayo_osipitan.model;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.osicorp.adebayo_osipitan.App;
import com.osicorp.adebayo_osipitan.view.activities.SplashView;

public class GenUtilities {


    private static Handler handler;

    public static void exitApp(Activity activity){
        if(Build.VERSION.SDK_INT >= 21) activity.finishAndRemoveTask();
        else activity.finish();
        Intent intent = new Intent(activity, SplashView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        activity.startActivity(intent);
    }

    public static Handler getHandler() {
        return handler;
    }

    public static void  message ( String message){
        Toast.makeText(App.context, message, Toast.LENGTH_SHORT).show();
    }

    public static void pulse(final View view){
        final ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat("scaleX", 0.75f),
                PropertyValuesHolder.ofFloat("scaleY", 0.75f));
        scaleDown.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleDown.setDuration(500);
        scaleDown.setRepeatCount(1);
        scaleDown.setRepeatMode(ObjectAnimator.REVERSE);
        scaleDown.reverse();
        scaleDown.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                view.setScaleX(0);
                view.setScaleY(0);
            }
        });
        scaleDown.start();
    }

    public static void transitionActivity(Activity oldActivity, Class newActivity) {
        Activity activity = oldActivity;
        if (Build.VERSION.SDK_INT >= 21) activity.finishAndRemoveTask();
        else activity.finish();

        //oldActivity.overridePendingTransition(R.anim.from_middle, R.anim.to_middle);
        oldActivity.startActivity(new Intent(oldActivity, newActivity));
    }

    public static void transitionActivity(Activity oldActivity, Intent intent){
        if (Build.VERSION.SDK_INT >= 21) oldActivity.finishAndRemoveTask();
        else oldActivity.finish();
        oldActivity.startActivity(intent);
    }
}
