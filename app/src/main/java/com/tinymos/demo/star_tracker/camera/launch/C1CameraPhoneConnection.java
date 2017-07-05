package com.tinymos.demo.star_tracker.camera.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.camera.C2Camera;
import com.tinymos.demo.star_tracker.R;


public class C1CameraPhoneConnection extends Activity {


    private Handler handler = new Handler();


    private Runnable backGroundTask = new Runnable() {

        public void run() {

            if (Global.cameraPhoneConnected){
                next();
            }else {
                handler.postDelayed(this, 2000); // refresh every 1000 ms = 1 sec
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_loading);

        TextView text = (TextView)findViewById(R.id.text);
        text.setText(R.string.waiting);

        Thread cameraServerThread = new CameraServerThread();
        cameraServerThread.start();


        backGroundTask.run();
    }

    @Override
    public void onBackPressed() {
        //disable backbutton
    }


    public void next()
    {
        Intent intent = new Intent(C1CameraPhoneConnection.this, C2Camera.class);
        startActivity(intent);
    }
}
