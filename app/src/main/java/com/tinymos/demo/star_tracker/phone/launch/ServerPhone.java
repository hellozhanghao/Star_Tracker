package com.tinymos.demo.star_tracker.phone.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.tinymos.demo.star_tracker.Constant;
import com.tinymos.demo.star_tracker.R;
import com.tinymos.demo.star_tracker.helper.BackgroudThread;
import com.tinymos.demo.star_tracker.helper.Connect;

public class ServerPhone extends Activity {



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

            if (Constant.devicesReady){
                runPhone();
            }else {
                handler.postDelayed(this, 2000); // refresh every 1000 ms = 1 sec
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_server_phone);

        //Check connection status
        if (!Constant.isServerConnected()){
            Log.i("Connection","New Connection to Server");
            Thread connect = new Connect("phone");
            connect.start();
        }
        Log.i("Connection","Connection OK");
        backGroundTask.run();
    }


    public void runPhone()
    {
        Intent intent = new Intent(ServerPhone.this, Init.class);
        startActivity(intent);
    }
}
