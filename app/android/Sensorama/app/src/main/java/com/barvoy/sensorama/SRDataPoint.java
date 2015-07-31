package com.barvoy.sensorama;

import java.io.BufferedWriter;

public class SRDataPoint {
    String  prefix;
    Float[] points;
    int     what;

    public SRDataPoint(String _prefix, Float []_points)
    {
        prefix = _prefix;
        points = _points;
    }

    public String debugString() {
        String s = "";

        for (Float f : points) {
            s += String.format("%f ", f);
        }
        s += "\n";
        return s;
    }

    public void dump(BufferedWriter fo)
    {
        String s;
        boolean isFirst;

        try {
            fo.write("{ '%s' : [ " + prefix);
            isFirst = true;
            for (Float f : points) {
                isFirst = false;
                fo.write(String.format("%s%f", isFirst ? "" : ",", f));
            }
            fo.write("] }\n");
        } catch (Exception e) {
            SRDbg.l("Got exception: " + e.toString());
        }
    }
}
