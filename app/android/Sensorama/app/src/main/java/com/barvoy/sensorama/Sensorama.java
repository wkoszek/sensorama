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
    SRGravity gravity;
    SRStepCounter stepCounter;
    SREnviron environ;

    List<SRDataPointList> points;
    boolean enabled;

    public Sensorama(Activity activity)
    {
        points = new ArrayList<SRDataPointList>();
        accel = new SRAccel(activity);
        gyro = new SRGyro(activity);
        gravity = new SRGravity(activity);
        stepCounter = new SRStepCounter(activity);
        environ = new SREnviron(activity);
        battery = new SRBattery();
    }

    public void capture() {
        SRDataPointList list = new SRDataPointList();
        accel.capture(list);
        gyro.capture(list);
        gravity.capture(list);
        stepCounter.capture(list);
        environ.capture(list);
        battery.capture(list);

        points.add(list);
    }

    public void dumpPoints(BufferedWriter fo) throws IOException {
        boolean isFirst = true;
        for (SRDataPointList list : points) {
            fo.write("     " + (isFirst ? " " : ",") + "{");
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
