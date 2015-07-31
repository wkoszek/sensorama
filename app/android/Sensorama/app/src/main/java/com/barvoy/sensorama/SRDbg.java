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
