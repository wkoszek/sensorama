package com.barvoy.sensorama;


import java.io.BufferedWriter;
import java.io.IOException;

public class SRJSON {
    public SRJSON() {

    }

    public String qq(String s) {
        return "\"" + s + "\"";
    }

    public void dump(Sensorama S, BufferedWriter fo, String dateStr) throws IOException {
        try {
            fo.write("{\n");
            fo.write(String.format("   %s : %s,\n", qq("date"), qq(dateStr)));
            fo.write(String.format("   %s : [\n", qq("points")));
            S.dumpPoints(fo);
            fo.write("   ]\n");
            fo.write("}\n");
        } catch (Exception e) {
            throw e;
        }
    }

}
