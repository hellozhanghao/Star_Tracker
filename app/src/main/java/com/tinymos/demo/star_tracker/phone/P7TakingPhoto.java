package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class P7TakingPhoto extends Activity {


    private Handler handler = new Handler();

    private Runnable backGroundTask = new Runnable() {

        public void run() {
            if (Global.PICTURE_TAKEN){
                next();
            }else {
                handler.postDelayed(this, 2000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_taking_photo);

        Thread thread = new P7TakingPhotoBackgroundThread();
        thread.start();

        backGroundTask.run();
    }

    @Override
    public void onBackPressed() {
        //disable backbutton
    }

    public void next(){
        Intent i = new Intent(P7TakingPhoto.this, P4SearchStar.class);
        startActivity(i);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

}
