package com.tinymos.demo.star_tracker.phone.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class P1CameraPhoneConnection extends Activity {



    private Handler handler = new Handler();


    private Runnable checkConnection = new Runnable() {

        public void run() {

            if (Global.cameraPhoneConnected){
                next();
            }else {
                handler.postDelayed(this, 1000); // refresh every 1000 ms = 1 sec
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_loading);

        TextView text = (TextView)findViewById(R.id.text);
        text.setText(R.string.waiting);


        Log.i("Connection","Connection OK");

        checkConnection.run();

        Thread phoneToCameraConnectionThread = new PhoneToCameraConnectionThread();
        phoneToCameraConnectionThread.start();
    }

    @Override
    public void onBackPressed() {
        //disable backbutton
    }


    public void next()
    {
        Intent intent = new Intent(P1CameraPhoneConnection.this, P2PiPhoneConnection.class);

        startActivity(intent);
    }
}
