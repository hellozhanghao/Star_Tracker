package com.tinymos.demo.star_tracker.phone.launch;

import com.tinymos.demo.star_tracker.Global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;

/**
 * Created by zhanghao on 2017/6/21.
 */

public class PhoneToCameraConnectionThread extends Thread {
    public void run(){
        try {
            Global.cameraServerSocket = new ServerSocket(4323);
            Global.cameraSocket = Global.cameraServerSocket.accept();
            Global.cameraWriter = new PrintWriter(Global.cameraSocket.getOutputStream(), true);
            Global.cameraReader = new BufferedReader(new InputStreamReader(Global.cameraSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
