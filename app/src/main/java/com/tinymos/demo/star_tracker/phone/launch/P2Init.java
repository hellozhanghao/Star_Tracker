package com.tinymos.demo.star_tracker.phone.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;
import com.tinymos.demo.star_tracker.phone.P3Calibrating;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class P2Init extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //to avoid network on main thread exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        while (Global.piWriter == null){
            try {
                Global.PiSocket = new Socket(Global.piIP, 4322);
                Global.piWriter = new PrintWriter(Global.PiSocket.getOutputStream(), true); //set true for autoflush
                Global.piReader = new BufferedReader(new InputStreamReader(Global.PiSocket.getInputStream()));
                Global.piWriter.println("hello");
                if (Global.piReader.readLine().equals("hi")){
                    Log.i("Connection","Connected to PI");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Thread thread = new PhoneToCameraThread();
        thread.start();


    }

    public void onCalibrateButtonClicked(View view)
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
