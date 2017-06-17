package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.Constant;
import com.tinymos.demo.star_tracker.MainActivity;
import com.tinymos.demo.star_tracker.R;
import com.tinymos.demo.star_tracker.Splash;

public class TrackingProgress extends Activity {

    String starName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_progress);

        Bundle bundle = getIntent().getExtras();
        Toast.makeText(getApplication(),bundle.getString("name"), Toast.LENGTH_LONG).show();
        Constant.piWriter.println("star "+bundle.getString("name"));

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
