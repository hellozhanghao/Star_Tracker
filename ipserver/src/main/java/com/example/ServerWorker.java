package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/5.
 */

public class ServerWorker extends Thread {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    ServerWorker(Socket socket,BufferedReader in, PrintWriter out){
        this.socket = socket;
        this.in = in;
        this.out = out;
    }
    public void run() {
        while (true){
            try{
                String message = in.readLine();
                if (message==null) continue;
                switch (message) {
                    case "connect":
                        if (Server.validConnection()) out.println(Server.getIPs());
                        else out.println("Checkout later");
                        break;
                    case "bye":
                        socket.close();
                        break;
                    default:
                        String[] messages = message.split(":");
                        System.out.println();
                        break;
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

}
