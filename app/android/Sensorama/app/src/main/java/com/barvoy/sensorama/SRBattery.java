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
        context = SRApp.getAppContext();
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean batteryIsCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
        boolean batteryIsFull = status == BatteryManager.BATTERY_STATUS_FULL;

        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean batteryUsbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean batteryAcCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPercent = 100 * (level / (float)scale);
        int batteryState = ((batteryIsCharging ? 1 : 0) |
                        (batteryIsFull     ? 2 : 0) |
                        (batteryUsbCharge  ? 4 : 0) |
                        (batteryAcCharge   ? 8 : 0));

        SRDataPoint pointPercent = new SRDataPoint("bat", new Float[]{ batteryPercent } );
        SRDbg.l("bat:" + pointPercent.debugString());

        SRDataPoint pointState = new SRDataPoint("bats", new Float[] { (float)batteryState});
        SRDbg.l("bats:" + pointState.debugString());

        list.add(pointPercent);
        list.add(pointState);
    }
}
