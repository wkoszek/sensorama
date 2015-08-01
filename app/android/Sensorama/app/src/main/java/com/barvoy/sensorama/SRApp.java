package com.barvoy.sensorama;


import android.app.Application;
import android.content.Context;

public class SRApp extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        SRApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return SRApp.context;
    }
}
