// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

import android.app.Activity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;


public class Sensorama {
    SRAccel accel;
    SRBattery battery;
    SRGyro gyro;

    List<SRDataPointList> points;
    boolean enabled;

    public Sensorama(Activity activity, boolean debuggingEnabled)
    {
        SRDbg.debugEnable();
        points = new ArrayList<SRDataPointList>();
        accel = new SRAccel(activity);
        gyro = new SRGyro(activity);
        battery = new SRBattery();
    }

    public void capture() {
        SRDataPointList list = new SRDataPointList();
        battery.capture(list);
        accel.capture(list);
        gyro.capture(list);
        points.add(list);
    }

    public void dumpPoints(BufferedWriter fo) throws IOException {
        boolean isFirst = true;
        for (SRDataPointList list : points) {
            fo.write("     " + (isFirst ? "" : ",") + "{");
            list.dump(fo);
            fo.write("       }\n");
            isFirst = false;
        }
        points = new ArrayList<SRDataPointList>();
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
