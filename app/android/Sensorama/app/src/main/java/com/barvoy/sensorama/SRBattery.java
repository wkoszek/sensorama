package com.barvoy.sensorama;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.FloatMath;

import com.barvoy.sensorama.Sensorama;

public class SRBattery extends BroadcastReceiver {
    boolean batteryIsCharging;
    boolean batteryUsbCharge;
    boolean batteryAcCharge;
    float batteryPercent;

    @Override
    public void onReceive(Context context, Intent intent) {
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
    }

    public void capture(SRDataPointList list) {
        SRDataPoint point = new SRDataPoint("bat", new Float[]{ batteryPercent } );
        SRDbg.l("bat:" + point.debugString());
        list.add(point);
    }
}
