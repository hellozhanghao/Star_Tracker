package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/21.
 */

public class FakePI {

    public static void getMessage(BufferedReader in) throws IOException {
        String message = in.readLine();
        System.out.println("Message from phone:"+message);
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        Socket socket_temp = new Socket(TestGlobal.serverIP, 4321);
        PrintWriter serverOut = new PrintWriter(socket_temp.getOutputStream(), true);
        BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket_temp.getInputStream()));
        serverOut.println("pi");
        serverOut.print("bye");

        // TODO: 2017/6/21 Keep Socket open for now

        socket_temp.close();

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


        getMessage(in);
        Thread.sleep(4000);
        out.println("NORTH FOUND");
        System.out.println("NORTH FOUND");

        getMessage(in);
        Thread.sleep(4000);
        out.println("STAR FOUND");
        System.out.println("STAR FOUND");

    }
}
