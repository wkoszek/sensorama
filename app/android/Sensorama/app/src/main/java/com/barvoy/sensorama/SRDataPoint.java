package com.barvoy.sensorama;

public class SRDataPoint {
    String  prefix;

    public SRDataPoint(String _prefix)
    {
        prefix = _prefix;
    }

    public void dump()
    {
        System.out.printf("%s ", prefix);
        System.out.println("");
    }
}
