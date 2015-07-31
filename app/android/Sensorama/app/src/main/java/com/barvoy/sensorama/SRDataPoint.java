package com.barvoy.sensorama;

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

    public void dump()
    {
        String s;
        boolean isFirst;

        System.out.printf("{ '%s' : [ ", prefix);
        isFirst = true;
        for (Float f : points) {
            isFirst = false;
            System.out.printf("%s%f", isFirst ? "" : ",", f);
        }
        System.out.printf("] }\n");
    }
}
