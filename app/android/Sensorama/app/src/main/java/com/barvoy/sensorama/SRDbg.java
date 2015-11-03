// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

public class SRDbg {
    private static boolean debugEnabled;

    public SRDbg() {
        debugEnabled = false;
    }
    public static void debugEnable () {
        debugEnabled = true;
    }
    public static void debugDisable () {
        debugEnabled = false;
    }
    public static void l(String s) {
        if (!SRDbg.debugEnabled) {
            return;
        }
        System.out.println(s);
    }
}
