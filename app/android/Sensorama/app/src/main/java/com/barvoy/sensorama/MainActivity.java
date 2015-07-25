package com.barvoy.sensorama;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.barvoy.sensorama.Sensorama;
import com.barvoy.sensorama.SRDataPoint;

public class MainActivity extends ActionBarActivity {

    public Sensorama S;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        S = new Sensorama();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void recordingStartEnd(View view) {
        // Do something in response to button
        System.out.println("recordingStartEnd");
        SRDataPoint point = new SRDataPoint("sample");
        S.addPoint(point);
    }

    public void recordingShare(View view) {
        // Do something in response to button
        System.out.println("recordingShare");
        S.dumpPoints();
    }
}
