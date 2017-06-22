package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static String phoneIp;
    public static String cameraIP;
    public static String piIP;


    public static boolean validConnection(){
        if (phoneIp != null & cameraIP != null & piIP != null){
            return true;
        }else{
            return false;
        }
    }

    public static String getIPs(){
        return phoneIp+";"+cameraIP+";"+piIP;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4321);
        System.out.println("Waiting for connection...");
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String message = in.readLine();
            System.out.println(message+" connected");
            switch (message) {
                case "phone":
                    phoneIp = socket.getInetAddress().toString().replace("/","");
                    new ServerWorker(socket,in,out).start();
                    break;
                case "camera":
                    cameraIP = socket.getInetAddress().toString().replace("/","");
                    // TODO: 2017/6/20 add camera worker
                    new ServerWorker(socket,in, out).start();
                    break;
                case "pi":
                    piIP = socket.getInetAddress().toString().replace("/","");
                    // TODO: 2017/6/20 add pi worker
                    new ServerWorker(socket,in, out).start();
                    break;
            }
        }
    }
}
