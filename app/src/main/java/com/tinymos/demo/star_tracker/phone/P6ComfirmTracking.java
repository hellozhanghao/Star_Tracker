package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class P6ComfirmTracking extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_comfirm_tracking);
    }

    @Override
    public void onBackPressed() {
        //disable backbutton
    }

    public void takePhoto(View view)
    {
        Intent intent = new Intent(P6ComfirmTracking.this, P7TakingPhoto.class);
        Global.piWriter.println("TRACKING 120");
        startActivity(intent);
    }
}
