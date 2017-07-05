package com.tinymos.demo.star_tracker.camera;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/21.
 */

public class C2CameraBackGroundThread extends Thread {
    public void run(){


        while(true){
            try{
                String message = Global.cameraReader.readLine();
                if (message.equals("press")) Global.press();
            }catch (Exception e){
                Log.e("Camera",e.getMessage());
            }
        }
    }

}
