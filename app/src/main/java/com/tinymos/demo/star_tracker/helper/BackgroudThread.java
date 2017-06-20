package com.tinymos.demo.star_tracker.helper;

import android.util.Log;

import com.tinymos.demo.star_tracker.Constant;

import java.io.IOException;

/**
 * Created by zhanghao on 2017/6/20.
 */

public class BackgroudThread extends Thread {

    String message;

    public void run(){
        if (Constant.isServerConnected()){
            try {
                Constant.serverOut.println("connect");
                message = Constant.serverIn.readLine();

                if (message.equals("Connection OK")){
                    Log.i("Connection","Launcing Camera Activity");
                    Constant.devicesReady = true;
                }else {
                    Log.i("Connection","Other Devices Not Ready");
                    Constant.devicesReady = false;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
