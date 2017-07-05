package com.tinymos.demo.star_tracker.phone.launch;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by zhanghao on 2017/6/21.
 */

public class PhoneToCameraConnectionThread extends Thread {
    public void run(){
        while (Global.cameraWriter == null){
            try {
                Global.cameraSocket = new Socket(Global.cameraIP, 4321);
                Global.cameraWriter = new PrintWriter(Global.cameraSocket.getOutputStream(), true); //set true for autoflush
                Global.cameraReader = new BufferedReader(new InputStreamReader(Global.cameraSocket.getInputStream()));
                if (Global.cameraReader.readLine().equals("hello")){
                    Global.cameraWriter.println("hi");
                    Log.i("Connection","Connected to Camera");
                    Global.cameraPhoneConnected = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
