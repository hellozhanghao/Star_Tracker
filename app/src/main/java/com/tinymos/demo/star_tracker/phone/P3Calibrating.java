package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class P3Calibrating extends Activity {


    private Handler handler = new Handler();

    private Runnable backGroundTask = new Runnable() {

        public void run() {
            if (Global.NORTH_FOUND){
                next();
            }else {
                handler.postDelayed(this, 2000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_loading);

        TextView text = (TextView)findViewById(R.id.text);
        text.setText(R.string.calibrating);

        //waiting for pi to response
        Thread thread = new P3CalibratingBackGroundThread();
        thread.start();

        //checking if pi is ready
        backGroundTask.run();
    }

    private void next(){
        Intent i = new Intent(P3Calibrating.this, P4SearchStar.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }


}
