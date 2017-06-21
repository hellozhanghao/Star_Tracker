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


                if (message.equals("Checkout later")){
                    Log.i("Connection","Other Devices Not Ready");
                    Constant.devicesReady = false;
                }else {
                    Log.i("Connection","Launcing Next Activity");
                    Log.i("Connection",message);
                    String[] ips = message.split(";");
                    Constant.phoneIP = ips[0];
                    Constant.cameraIP = ips[1];
                    Constant.piIP = ips[2];
                    Constant.devicesReady = true;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
