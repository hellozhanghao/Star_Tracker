package com.tinymos.demo.star_tracker.camera.launch;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Hao on 7/4/2017.
 */

public class CameraServerThread extends Thread {
    public void run(){
        try{
            Global.cameraServerSocket = new ServerSocket(4321);
            Global.cameraSocket = Global.cameraServerSocket.accept();
            Global.cameraWriter = new PrintWriter(Global.cameraSocket.getOutputStream(), true);
            Global.cameraReader = new BufferedReader(new InputStreamReader(Global.cameraSocket.getInputStream()));

            Global.cameraWriter.println("hello");
            if (Global.cameraReader.readLine().equals("hi")){
                Log.i("Connection","Connected to Phone");
                Global.cameraPhoneConnected = true;
            }

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

    }
}
