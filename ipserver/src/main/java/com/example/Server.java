package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static String phoneIp;
    static String cameraIP;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4321);
        while (true) {
            Socket client = serverSocket.accept();
            new ServerWorker(client).run();
        }
    }
}
