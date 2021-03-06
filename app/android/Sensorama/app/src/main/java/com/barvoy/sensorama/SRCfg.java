// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

import android.hardware.Sensor;
import java.util.List;

public class SRCfg {
    public static final String addDirName = "sensorama";
    public static final int interval = 250;
    public static final String timeFormat = "HHmmss";
    public static final String dateFormat = "yyyyMMdd_" + timeFormat;
    public static final int buttonColorStopped = 0xffff0000;    // red
    public static final int buttonColorStart = 0xff00ff00;      // green
    public static final int buttonColorInitial = buttonColorStart;
    public static final boolean doParseBootstrap = true;
    public static final boolean doPressureMonitoring = false;
    public static final boolean doDebug = false;

    // Public data. Should land somewhere else maybe.
    public static List<Sensor> sensorList;
    public static String deviceName;
}
