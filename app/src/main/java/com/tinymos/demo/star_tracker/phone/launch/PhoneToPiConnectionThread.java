package com.tinymos.demo.star_tracker.phone.launch;

import android.util.Log;

import com.tinymos.demo.star_tracker.Global;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/22.
 */

public class PhoneToPiConnectionThread extends Thread {
    public void run(){
        while (Global.piWriter == null){
            try {
                Global.PiSocket = new Socket(Global.piIP, 5003);
                Global.piWriter = new PrintWriter(Global.PiSocket.getOutputStream(), true); //set true for autoflush
                Global.piReader = new BufferedReader(new InputStreamReader(Global.PiSocket.getInputStream()));
                Global.piWriter.println("hello");
                String message = Global.piReader.readLine();
                if (message.equals("hi")){
                    Log.i("Connection","Connected to PI");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
