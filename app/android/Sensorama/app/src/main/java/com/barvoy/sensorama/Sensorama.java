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

    public static final int SR_DATAPOINT_BATTERY = 1;
    public static final int SR_DATAPOINT_ACC = 2;

    List<SRDataPoint> points;
    boolean enabled;

    public Sensorama(Activity activity)
    {
        points = new ArrayList<SRDataPoint>();
        accel = new SRAccel(activity);
        battery = new SRBattery();
    }

    public void addPoint(SRDataPoint point)
    {
        points.add(point);
    }

    public void capture() {
        battery.capture(this);
        accel.capture(this);
    }

    public void dumpPoints() {

        for (SRDataPoint point : points) {
            point.dump();
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
