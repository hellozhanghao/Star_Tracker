package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/5.
 */

public class ServerWorker extends Thread {

    private Socket client;

    ServerWorker(Socket client){
        this.client = client;
    }
    public void run() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String message = bufferedReader.readLine();
            if (message.equals("I am a Phone")){
                Server.phoneIp = client.getInetAddress().toString().replace("/","");
            }
            else if (message.equals("I am a Camera")){
                Server.cameraIP = client.getInetAddress().toString().replace("/","");
            }
            else if (message.equals("request ip")){
                PrintWriter printWriter = new PrintWriter(client.getOutputStream(), true);
                if (Server.cameraIP != null & Server.phoneIp != null){
                    printWriter.println("phone:"+Server.phoneIp+";camera:"+Server.cameraIP);
                } else {
                    printWriter.println("checkout later");
                }
            }
            client.close();
            System.out.println("Address: Phone:"+Server.phoneIp +" Camera:"+Server.cameraIP);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
