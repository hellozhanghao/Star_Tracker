package com.tinymos.demo.star_tracker.camera.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.camera.CameraActivity;
import com.tinymos.demo.star_tracker.helper.BackgroudThread;
import com.tinymos.demo.star_tracker.helper.ConnectionThread;
import com.tinymos.demo.star_tracker.R;


public class ServerCameraConnection extends Activity {


    private Handler handler = new Handler();


    private Runnable backGroundTask = new Runnable() {

        public void run() {
            Thread backGroundThread = new BackgroudThread();
            backGroundThread.start();
            try {
                backGroundThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Global.devicesReady){
                runCamera();
            }else {
                handler.postDelayed(this, 2000); // refresh every 1000 ms = 1 sec
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_screen);


        //Check connection status
        if (!Global.isServerConnected()){
            Log.i("Connection","New Connection to Server");
            Thread connect = new ConnectionThread("camera");
            connect.start();
        }
        Log.i("Connection","Connection OK");
        backGroundTask.run();
    }


    public void runCamera()
    {
        Intent intent = new Intent(ServerCameraConnection.this, CameraActivity.class);
        startActivity(intent);
    }
}
