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
        while (Global.cameraSocket == null) try {
            Global.cameraSocket = new Socket(Global.phoneIP, 4323);
            Global.cameraWriter = new PrintWriter(Global.cameraSocket.getOutputStream(), true);
            Global.cameraReader = new BufferedReader(new InputStreamReader(Global.cameraSocket.getInputStream()));
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        while(true){
            try{
                Global.cameraReader.readLine();
                Global.press();
            }catch (Exception e){
                Log.e("Camera",e.getMessage());
            }
        }
    }

}
