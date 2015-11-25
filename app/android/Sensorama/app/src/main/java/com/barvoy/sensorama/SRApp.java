// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;
import com.barvoy.sensorama.BuildConfig;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import io.fabric.sdk.android.Fabric;

public class SRApp extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Parse.enableLocalDatastore(this);
        Parse.initialize(this,
                BuildConfig.PARSE_API_ID,
                BuildConfig.PARSE_CLIENT_ID
        );
        SRApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return SRApp.context;
    }
}
