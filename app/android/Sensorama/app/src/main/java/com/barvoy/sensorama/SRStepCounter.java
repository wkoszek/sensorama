// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SRStepCounter extends Activity implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor mStepCounter;
    float []vals = { 0 };

    public SRStepCounter(Activity activity) {
        mSensorManager = (SensorManager)activity.getSystemService(SENSOR_SERVICE);
        mStepCounter = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mStepCounter, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        vals[0] = event.values[0];
    }

    public void capture(SRDataPointList list)
    {
        SRDataPoint point = new SRDataPoint("steps", new Float[]{ vals[0] } );
        SRDbg.l("steps:" + point.debugString());
        list.add(point);
    }
}
