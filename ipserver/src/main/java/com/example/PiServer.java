package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.SyncFailedException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/21.
 */

public class PiServer extends Thread {


    public static void main(String[] args) {
        try {


            Socket socket_temp = new Socket("10.12.44.104", 4321);
            PrintWriter serverOut = new PrintWriter(socket_temp.getOutputStream(), true);
            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket_temp.getInputStream()));
            serverOut.println("pi");
            serverOut.print("bye");


            // TODO: 2017/6/21 Keep Socekt open for now
//            socket_temp.close();



            long startTime = System.currentTimeMillis();

            ServerSocket serverSocket = new ServerSocket(4322);
            Socket socket = serverSocket.accept();
            System.out.println("Waiting for connection");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = in.readLine();
            if (message.equals("hello")){
                out.println("hi");
                System.out.println("Connection Established");
            }
            message = in.readLine();
            while (message!=null){
                String[] messages = message.split(";");
                switch (messages[0]){
                    case "ready?":
                        if (System.currentTimeMillis()-startTime >3000){
                            out.println("ready");
                        }else {
                            out.println("not ready");
                        }
                    case "star":
                        System.out.println("Received Star: "+messages[1]);
                        out.println("star received");
                        break;

                    case "calibrate":
                        System.out.println("Calibrating");
                        // TODO: 2017/6/21 Do calibration here
                        break;
                    case "abort":
                        if (messages[1].equals("true")){
                            // TODO: 2017/6/21 Abort
                        }else {
                            // TODO: 2017/6/21 Continue
                        }
                        break;
                    case "photo":
                        int exposure = Integer.decode(messages[1]);
                        // TODO: 2017/6/21 Take photo with exposure
                        break;



                }
                message = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
