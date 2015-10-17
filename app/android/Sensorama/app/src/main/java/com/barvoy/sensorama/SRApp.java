package com.barvoy.sensorama;


import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseCrashReporting;
import com.barvoy.sensorama.SRAPIPerms;

public class SRApp extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        ParseCrashReporting.enable(this);

        Parse.enableLocalDatastore(this);
        Parse.initialize(this,
                SRAPIPerms.parseAPIID,
                SRAPIPerms.parseCLIID
        );
        SRApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return SRApp.context;
    }
}
