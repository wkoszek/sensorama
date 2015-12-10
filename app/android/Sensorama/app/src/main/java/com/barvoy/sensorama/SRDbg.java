// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

public class SRDbg {
    public SRDbg() {
    }

    public static void l(String s) {
        if (!SRCfg.doDebug) {
            return;
        }
        System.out.println(s);
    }
}
