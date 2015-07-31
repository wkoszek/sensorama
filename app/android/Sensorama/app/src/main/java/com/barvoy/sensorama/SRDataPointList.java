package com.barvoy.sensorama;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

public class SRDataPointList {
    List<SRDataPoint> dataPoints;
    static int sampleId;

    public SRDataPointList() {
        dataPoints = new ArrayList<SRDataPoint>();
    }

    public void add(SRDataPoint point) {
        dataPoints.add(point);
        sampleId++;
    }

    public void dump(BufferedWriter fo) {
        for (SRDataPoint point : dataPoints) {
            point.dump(fo);
        }
    }
}
