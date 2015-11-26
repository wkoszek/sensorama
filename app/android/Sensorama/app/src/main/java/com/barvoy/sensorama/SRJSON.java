// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;


import android.hardware.Sensor;
import java.io.BufferedWriter;
import java.io.IOException;

public class SRJSON {
    public SRJSON() {

    }

    public String qq(String s) {
        return "\"" + s + "\"";
    }

    public void dumpSensors(BufferedWriter fo) throws IOException {
        int whichItem = 0;

        fo.write("   \"sensors\": [\n");
        for (Sensor sensor : SRCfg.sensorList) {
            fo.write(String.format("        %s{\n", whichItem == 0 ? " " : ","));
            fo.write(String.format("              %s : %s,\n", qq("name"), qq(sensor.getName())));
            fo.write(String.format("              %s : %s,\n", qq("vendor"), qq(sensor.getVendor())));
            fo.write(String.format("              %s : %d,\n", qq("version"), sensor.getVersion()));
            fo.write(String.format("              %s : %d,\n", qq("type"), sensor.getType()));
            fo.write(String.format("              %s : %f,\n", qq("maxrange"), sensor.getMaximumRange()));
            fo.write(String.format("              %s : %f,\n", qq("resolution"), sensor.getResolution()));
            fo.write(String.format("              %s : %f,\n", qq("power"), sensor.getPower()));
            fo.write(String.format("              %s : %d\n", qq("mindelay"), sensor.getMinDelay()));
            fo.write(String.format("         }\n"));
            whichItem++;
        }
        fo.write("   ],\n");
    }

    public void dump(Sensorama S, BufferedWriter fo, String dateStr, int interval, String deviceName, String sampleName) throws IOException {
        try {

            SRCfg.deviceName = deviceName;

            fo.write("{\n");
            fo.write(String.format("   %s : %s,\n", qq("date"), qq(dateStr)));
            fo.write(String.format("   %s : %s,\n", qq("device"), qq(deviceName)));
            fo.write(String.format("   %s : %s,\n", qq("desc"), qq(sampleName)));
            fo.write(String.format("   %s : %d,\n", qq("interval"), interval));
            dumpSensors(fo);

            fo.write(String.format("   %s : [\n", qq("points")));
            S.dumpPoints(fo);
            fo.write("   ]\n");
            fo.write("}\n");
        } catch (Exception e) {
            throw e;
        }
    }
}
