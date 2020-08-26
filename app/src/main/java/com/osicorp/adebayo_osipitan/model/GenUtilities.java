package com.osicorp.adebayo_osipitan.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.widget.Toast;

import com.osicorp.adebayo_osipitan.App;
import com.osicorp.adebayo_osipitan.view.SplashView;

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

    public static void  message (Context context, String message){
        Toast.makeText(App.context, message, Toast.LENGTH_SHORT).show();
    }
}
