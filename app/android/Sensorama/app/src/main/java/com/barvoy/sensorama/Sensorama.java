package com.barvoy.sensorama;

import android.app.Activity;

import java.io.BufferedWriter;
import java.util.*;


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

    public void dumpPoints(BufferedWriter fo) {
        for (SRDataPointList list : points) {
            list.dump(fo);
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
