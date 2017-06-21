package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class TrackingProgress extends Activity {

    String starName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_tracking_progress);

        Bundle bundle = getIntent().getExtras();
        Global.piWriter.println("STARNAME "+bundle.getString("name"));

        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent i = new Intent(TrackingProgress.this, ComfirmTracking.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, secondsDelayed * 1000);

    }
}
