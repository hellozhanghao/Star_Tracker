package com.tinymos.demo.star_tracker.phone.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.tinymos.demo.star_tracker.Constant;
import com.tinymos.demo.star_tracker.R;
import com.tinymos.demo.star_tracker.phone.Calibrating;

import java.io.IOException;
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
                Constant.PiSocket = new Socket(Constant.serverIP, 5001);
                Constant.piWriter = new PrintWriter(Constant.PiSocket.getOutputStream(), true); //set true for autoflush
                Constant.piWriter.println("Hello, connection Established");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }



    public void onCalibrateButtonClicked(View view)
    {
        Intent intent = new Intent(Init.this, Calibrating.class);
        Constant.piWriter.println("calibrate");
        startActivity(intent);
    }

}
