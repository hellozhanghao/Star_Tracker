package com.tinymos.demo.star_tracker.phone;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

/**
 * Created by zhanghao on 2017/6/22.
 */

public class P7TakingPhotoBackgroundThread extends Thread{
    public void run(){
        try {
            String message = Global.piReader.readLine();
            if (message.equals("PICTURE TAKEN")){
                Global.PICTURE_TAKEN = true;
            }
        }catch (Exception e){
            Log.i("Runtime","Taking Picture Backgourd Thread Exception");
        }
    }
}
