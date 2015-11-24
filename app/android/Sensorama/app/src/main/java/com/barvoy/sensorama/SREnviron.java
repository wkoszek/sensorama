// Copyright (c) 2015, Wojciech Adam Koszek <wojciech@koszek.com>
// All rights reserved.

package com.barvoy.sensorama;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SREnviron extends Activity implements SensorEventListener {
    private final SensorManager mSensorManager;
    private final Sensor mAmbTemp, mLight, mPressure, mHumidity;
    float []vals = { 0, 0, 0, 0 };

    public SREnviron(Activity activity) {
        mSensorManager = (SensorManager)activity.getSystemService(SENSOR_SERVICE);
        mAmbTemp = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);

        mSensorManager.registerListener(this, mAmbTemp, SensorManager.SENSOR_DELAY_GAME);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_GAME);
        if (SRCfg.doPressureMonitoring) {
            mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_GAME);
        }
        mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAmbTemp, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        if (SRCfg.doPressureMonitoring) {
            mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        }
        mSensorManager.registerListener(this, mHumidity, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
        int vi = -1;

        if (event.sensor == mAmbTemp) {
            vi = 0;
        } else if (event.sensor == mLight) {
            vi = 1;
        } else if (event.sensor == mPressure) {
            vi = 2;
        } else if (event.sensor == mHumidity) {
            vi = 3;
        }
        if (vi == -1) {
            System.out.println("Unknown event!");
            return;
        }
        System.out.println("vi = " + vi);
        vals[vi] = event.values[0];
    }

    public void capture(SRDataPointList list)
    {
        SRDataPoint point = new SRDataPoint("env", new Float[]{ vals[0], vals[1], vals[2], vals[3] } );
        SRDbg.l("env:" + point.debugString());
        list.add(point);
    }
}
