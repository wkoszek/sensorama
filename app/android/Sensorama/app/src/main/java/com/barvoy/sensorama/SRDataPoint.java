package com.barvoy.sensorama;

public class SRDataPoint {
    String  prefix;
    float   val;
    int     what;

    public SRDataPoint(String _prefix, float _val, int _what)
    {
        prefix = _prefix;
        val = _val;
        what = _what;
    }

    public void dump()
    {
        System.out.printf("%s %f\n", prefix, val);
    }
}
