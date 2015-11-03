// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

import java.io.BufferedWriter;
import java.io.IOException;
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

    public void dump(BufferedWriter fo) throws IOException {
        boolean isFirst = true;
        for (SRDataPoint point : dataPoints) {
            if (!isFirst) {
                fo.write(",");
            }
            point.dump(fo);
            isFirst = false;
        }
    }
}
