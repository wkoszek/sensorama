// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SRGravity extends Activity implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor mGravity;
    float []vals = { 0, 0, 0 };

    public SRGravity(Activity activity) {
        mSensorManager = (SensorManager)activity.getSystemService(SENSOR_SERVICE);
        mGravity = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mGravity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        vals[0] = event.values[0];
        vals[1] = event.values[1];
        vals[2] = event.values[2];
    }

    public void capture(SRDataPointList list)
    {
        SRDataPoint point = new SRDataPoint("grav", new Float[]{ vals[0], vals[1], vals[2] } );
        SRDbg.l("grav:" + point.debugString());
        list.add(point);
    }
}
