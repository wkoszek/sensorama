// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;


public class SRBattery {
    private static Context context;

    public void capture(SRDataPointList list) {
        boolean batteryIsCharging;
        boolean batteryUsbCharge;
        boolean batteryAcCharge;
        float batteryPercent;

        context = SRApp.getAppContext();
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        batteryIsCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        batteryUsbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        batteryAcCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        batteryPercent = level / (float)scale;


        SRDataPoint point = new SRDataPoint("bat", new Float[]{ batteryPercent } );
        SRDbg.l("bat:" + point.debugString());
        list.add(point);
    }
}
