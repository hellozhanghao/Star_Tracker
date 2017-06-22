package com.tinymos.demo.star_tracker.phone.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;
import com.tinymos.demo.star_tracker.phone.P3Calibrating;

public class P2Init extends Activity {



    private Handler handler = new Handler();

    private Runnable backGroundTask = new Runnable() {

        public void run() {
            if (Global.piWriter != null){
                onCalibrateRequested();
            }else {
                Log.d(this.toString(),"Not ready");
                handler.postDelayed(this, 2000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //to avoid network on main thread exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        Thread phoneToPiThread = new PhoneToPiConnectionThread();
        phoneToPiThread.start();

        Thread phoneToCameraThread = new PhoneToCameraConnectionThread();
        phoneToCameraThread.start();

        backGroundTask.run();

    }

    @Override
    public void onBackPressed() {
        //disable backbutton
    }

    public void onCalibrateButtonClicked(View view)
    {

        if (Global.piWriter != null){
            Intent intent = new Intent(P2Init.this, P3Calibrating.class);
            Global.piWriter.println("CALIBRATE TRUE");
            startActivity(intent);
        }else {
            Toast.makeText(getApplication(),"Tracker not ready", Toast.LENGTH_LONG).show();
        }
    }

    public void onCalibrateRequested()
    {
            Intent intent = new Intent(P2Init.this, P3Calibrating.class);
            Global.piWriter.println("CALIBRATE TRUE");
            startActivity(intent);
    }

    public void onCaptureButtonClicked(View view){
        if (Global.cameraWriter!=null){
            Global.cameraWriter.println("press");
        }else {
            Toast.makeText(getApplication(),"Camera not ready", Toast.LENGTH_LONG).show();

        }
    }

}
