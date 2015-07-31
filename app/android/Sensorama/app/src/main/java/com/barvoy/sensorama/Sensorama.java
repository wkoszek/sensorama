package com.barvoy.sensorama;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.BatteryManager;
import android.util.Log;

import java.util.*;
import com.barvoy.sensorama.SRDataPoint;


public class Sensorama {
    SRAccel accel;
    SRBattery battery;

    List<SRDataPointList> points;
    boolean enabled;

    public Sensorama(Activity activity, boolean debuggingEnabled)
    {
        SRDbg.debugEnable();
        points = new ArrayList<SRDataPointList>();
        accel = new SRAccel(activity);
        battery = new SRBattery();
    }

    public void capture() {
        SRDataPointList list = new SRDataPointList();
        battery.capture(list);
        accel.capture(list);
        points.add(list);
    }

    public void dumpPoints() {
        for (SRDataPointList list : points) {
            list.dump();
        }
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void enable(boolean _enabled)
    {
        enabled = _enabled;
    }

}
