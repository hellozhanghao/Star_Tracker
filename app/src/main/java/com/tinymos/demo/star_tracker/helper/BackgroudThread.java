package com.tinymos.demo.star_tracker.helper;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

import java.io.IOException;

/**
 * Created by zhanghao on 2017/6/20.
 */

public class BackgroudThread extends Thread {

    String message;

    public void run(){
        if (Global.isServerConnected()){
            try {
                Global.serverOut.println("connect");
                message = Global.serverIn.readLine();


                if (message.equals("Checkout later")){
                    Log.i("Connection","Other Devices Not Ready");
                    Global.ALL_DEVICES_READY = false;
                }else {
                    Log.i("Connection","Launcing Next Activity");
                    Log.i("Connection",message);
                    String[] ips = message.split(";");
                    Global.phoneIP = ips[0];
                    Global.cameraIP = ips[1];
                    Global.piIP = ips[2];
                    Global.ALL_DEVICES_READY = true;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
