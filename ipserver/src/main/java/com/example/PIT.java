package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Created by zhanghao on 2017/6/17.
 */

public class PIT {
    public static void main(String[] args) {
        try {
            Socket socket= new Socket("10.12.44.104", 5001);
            PrintWriter printWriter= new PrintWriter(socket.getOutputStream(), true); //set true for autoflush
            printWriter.println("Hello");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
