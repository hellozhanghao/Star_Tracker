package com.tinymos.demo.star_tracker.phone;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

/**
 * Created by zhanghao on 2017/6/21.
 */

public class P5TrackingProgressBackgroundThread extends Thread {

    public void run(){
        try {
            String message = Global.piReader.readLine();
            if (message.equals("STAR FOUND")){
                Global.STAR_FOUND = true;
            }
        }catch (Exception e){
            Log.i("Runtime","Tracking Progress Backgourd Thread Exception");
        }
    }
}
