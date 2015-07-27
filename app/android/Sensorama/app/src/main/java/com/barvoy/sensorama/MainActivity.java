package com.barvoy.sensorama;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.Timer;
import java.util.TimerTask;


import com.barvoy.sensorama.Sensorama;
import com.barvoy.sensorama.SRDataPoint;

public class MainActivity extends ActionBarActivity {

    public Sensorama S;
    public int sampleNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        S = new Sensorama();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.printf("en:%d %d", S.isEnabled() ? 1 : 0, sampleNumber);
                System.out.println("");
                sampleNumber++;
            }
        }, 0, 1000);
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
        Button buttonStartEnd = (Button) findViewById(view.getId());

        String textCurrent = buttonStartEnd.getText().toString();

        String textStart = "Start recording";
        if (textCurrent.equals(textStart)) {
            buttonStartEnd.setText("Stop recording");
            S.enable(true);
            System.out.println("Started");
        } else {
            buttonStartEnd.setText(textStart);
            S.enable(false);
            System.out.println("Stopped");
        }

        SRDataPoint point = new SRDataPoint("sample");
        S.addPoint(point);
    }

    public void recordingShare(View view) {
        // Do something in response to button
        System.out.println("recordingShare");
        S.dumpPoints();
    }
}
