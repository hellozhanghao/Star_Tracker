package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.TextView;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class P5TrackingProgress extends Activity {

    private Handler handler = new Handler();

    private Runnable backGroundTask = new Runnable() {

        public void run() {
            if (Global.STAR_FOUND){
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
        text.setText(R.string.searching);

        Bundle bundle = getIntent().getExtras();
        Global.piWriter.println("STARNAME "+bundle.getString("name"));

        Thread thread = new P5TrackingProgressBackgroundThread();
        thread.start();

        backGroundTask.run();

    }

    public void next(){
        Intent i = new Intent(P5TrackingProgress.this, P6ComfirmTracking.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}
