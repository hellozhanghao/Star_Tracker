package com.tinymos.demo.star_tracker.phone;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

/**
 * Created by zhanghao on 2017/6/21.
 */

public class P3CalibratingBackGroundThread extends Thread {

    public void run(){
        try {
            String message = Global.piReader.readLine();
            if (message.equals("NORTH FOUND")){
                Global.NORTH_FOUND = true;
            }
        }catch (Exception e){
            Log.i("Runtime","Calibrating Backgourd Thread Exception");
        }
    }
}
