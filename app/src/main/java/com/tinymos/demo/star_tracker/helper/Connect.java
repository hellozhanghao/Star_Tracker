package com.tinymos.demo.star_tracker.helper;

import android.util.Log;

import com.tinymos.demo.star_tracker.Constant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/20.
 */

public class Connect extends Thread {

    String device;

    public Connect(String device){
        this.device = device;
    }

    public void run() {
        try{
            Constant.serverSocket = new Socket(Constant.serverIP, Constant.serverPort);
            Constant.serverOut = new PrintWriter(Constant.serverSocket.getOutputStream(), true);
            Constant.serverIn = new BufferedReader(new InputStreamReader(Constant.serverSocket.getInputStream()));
            Constant.serverOut.println(device);
            Log.i("Connection","Server Connection Established");
        }catch (Exception e){
            Log.i("Connection","Error in network");
            Log.i("Connection",e.toString());
        }
    }
}
