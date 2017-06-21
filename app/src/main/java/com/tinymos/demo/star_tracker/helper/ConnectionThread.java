package com.tinymos.demo.star_tracker.helper;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/20.
 */

public class ConnectionThread extends Thread {

    String device;

    public ConnectionThread(String device){
        this.device = device;
    }

    public void run() {
        try{
            Global.serverSocket = new Socket(Global.serverIP, Global.serverPort);
            Global.serverOut = new PrintWriter(Global.serverSocket.getOutputStream(), true);
            Global.serverIn = new BufferedReader(new InputStreamReader(Global.serverSocket.getInputStream()));
            Global.serverOut.println(device);
            Log.i("Connection","Server Connection Established");
        }catch (Exception e){
            Log.i("Connection","Error in network");
            Log.i("Connection",e.toString());
        }
    }
}
