package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tinymos.demo.star_tracker.R;

public class P7TakingPhoto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_taking_photo);




        int secondsDelayed = 2;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                Intent i = new Intent(P7TakingPhoto.this, P4SearchStar.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, secondsDelayed * 1000);
    }

//    public void abort(View view)
//    {
//        Intent intent = new Intent(P7TakingPhoto.this, P4SearchStar.class);
//        Global.piWriter.println("abort");
//        startActivity(intent);
//    }
}
