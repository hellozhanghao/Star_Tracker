package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;

public class ComfirmTracking extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_comfirm_tracking);
    }

    public void takePhoto(View view)
    {
        Intent intent = new Intent(ComfirmTracking.this, TakingPhoto.class);
        Global.piWriter.println("TRACKING 120");
        startActivity(intent);
    }
}
