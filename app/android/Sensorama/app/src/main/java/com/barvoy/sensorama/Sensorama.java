package com.barvoy.sensorama;

import java.util.*;
import com.barvoy.sensorama.SRDataPoint;

public class Sensorama {
    List<SRDataPoint> points;
    boolean enabled;

    public Sensorama()
    {
        points = new ArrayList<SRDataPoint>();
    }

    public void addPoint(SRDataPoint point)
    {
        points.add(point);
    }

    public void dumpPoints()
    {

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
