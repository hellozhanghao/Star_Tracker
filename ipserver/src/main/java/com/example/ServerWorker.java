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

    private BufferedReader in;
    private PrintWriter out;

    ServerWorker(BufferedReader in, PrintWriter out){
        this.in = in;
        this.out = out;
    }
    public void run() {
        while (true){
            try{
                String message = in.readLine();
                if (message.equals("connect")){
                    if (Server.validConnection()) out.println("Connection OK");
                    else out.println("Checkout later");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

}
