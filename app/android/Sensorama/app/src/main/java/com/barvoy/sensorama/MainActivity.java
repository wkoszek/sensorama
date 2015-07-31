package com.barvoy.sensorama;

import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.os.StatFs;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


import com.barvoy.sensorama.Sensorama;
import com.barvoy.sensorama.SRDataPoint;

public class MainActivity extends Activity {

    public Sensorama S;
    public int sampleNumber;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SRDbg.debugEnable();

        storageDebug();

        S = new Sensorama(this, false);

        sampleUpdateDate(sampleDateFmt() + "...");

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.printf("en:%d %d", S.isEnabled() ? 1 : 0, sampleNumber);
                System.out.println("");
                if (S.isEnabled()) {
                    S.capture();
                }
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
            sampleUpdateDate(sampleDateFmt());
        } else {
            buttonStartEnd.setText(textStart);
            S.enable(false);
            System.out.println("Stopped");
            sampleUpdateDate(getSampleDate() + "-" + sampleDateFmt());
        }
    }

    public String sampleDateFmt() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH.mm");
        return sdf.format(new Date());
    }

    public String getSampleDate() {
        TextView userDate = (TextView)findViewById(R.id.userDate);
        return userDate.getText().toString();
    }

    public void sampleUpdateDate(String str) {
        // Set the date of the start
        TextView userDate = (TextView)findViewById(R.id.userDate);
        userDate.setText(str);
    }

    public void recordingShare(View view) {
        // Do something in response to button
        System.out.printf("recordingShare: %s\n", getSampleDate());

        S.dumpPoints();
    }

    public long spaceAvailableMB() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable = (long)stat.getBlockSize() *(long)stat.getBlockCount();
        long availableMB = bytesAvailable / 1048576;
        return availableMB;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void storageDebug() {
        if (!isExternalStorageWritable()) {
            SRDbg.l("Can't write to external storage");
        } else {
            SRDbg.l("Storage detected");
        }
        SRDbg.l("Free storage: " + spaceAvailableMB());
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(isSDPresent) {
            SRDbg.l("SD not available");
        } else {
            SRDbg.l("SD isn't available");
        }
    }
}
