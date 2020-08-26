package com.osicorp.adebayo_osipitan;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import java.util.LinkedList;
import java.util.List;


public class App extends Application {

    public static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

        List<Integer> m = new LinkedList<>();

    }
}
