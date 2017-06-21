package com.tinymos.demo.star_tracker.phone.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;

import com.tinymos.demo.star_tracker.Constant;
import com.tinymos.demo.star_tracker.R;
import com.tinymos.demo.star_tracker.phone.Calibrating;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Init extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        //to avoid network on main thread exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (Constant.piWriter == null){
            try {
                Constant.PiSocket = new Socket(Constant.piIP, 4322);
                Constant.piWriter = new PrintWriter(Constant.PiSocket.getOutputStream(), true); //set true for autoflush
                Constant.piReader = new BufferedReader(new InputStreamReader(Constant.PiSocket.getInputStream()));
                Constant.piWriter.println("hello");
                if (Constant.piReader.readLine().equals("hi")){
                    Log.i("Connection","Connected to PI");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }



    public void onCalibrateButtonClicked(View view)
    {
        Intent intent = new Intent(Init.this, Calibrating.class);
        Constant.piWriter.println("calibrate:");
        startActivity(intent);
    }

}
