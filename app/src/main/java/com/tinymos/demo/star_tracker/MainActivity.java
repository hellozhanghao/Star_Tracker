package com.tinymos.demo.star_tracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.tinymos.demo.star_tracker.camera.StartCameraActivity;
import com.tinymos.demo.star_tracker.phone.Init;
import com.tinymos.demo.star_tracker.phone.SearchStar;
import com.tinymos.demo.star_tracker.phone.StartPhoneActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
    }

    public void startPhone(View view)
    {
//        Intent intent = new Intent(MainActivity.this, StartPhoneActivity.class);
        Intent intent = new Intent(MainActivity.this, Init.class);
        startActivity(intent);
    }

    public void startCamera(View view)
    {
        Intent intent = new Intent(MainActivity.this, StartCameraActivity.class);
        startActivity(intent);
    }

}
