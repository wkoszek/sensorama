package com.barvoy.sensorama;

import android.app.Activity;

import java.io.BufferedWriter;
import java.io.IOException;
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

    public void dumpPoints(BufferedWriter fo) throws IOException {
        boolean isFirst = true;
        for (SRDataPointList list : points) {
            fo.write("     " + (isFirst ? "" : ",") + "{");
            list.dump(fo);
            fo.write("       }\n");
            isFirst = false;
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
