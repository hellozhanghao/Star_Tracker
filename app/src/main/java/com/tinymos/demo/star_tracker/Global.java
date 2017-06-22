package com.tinymos.demo.star_tracker;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/5.
 */

public class Global {

//    NORTH FOUND
//    STAR FOUND
//    TRACKER READY
//    PICTURE TAKEN

    /**
     * Server Communications
     * Set serverIP to static Network IP
     * */
    public static int serverPort =4321;
    public static String serverIP = "10.12.44.104";    //Zhang Hao's iMac
//    public static String serverIP = "10.12.87.140";  //Zhang Hao's Macbook Air

    //Sockets
    public static Socket serverSocket;
    public static BufferedReader serverIn;
    public static PrintWriter serverOut;

    public static boolean isServerConnected(){
        if (serverSocket!=null & serverIn!=null & serverOut!=null){
            return true;
        }else {
            return false;
        }
    }

    public static void resetServerConnection(){
        serverSocket = null;
        serverIn = null;
        serverOut = null;
    }


    /**
     * Global Boolean Variables
     */
    public static boolean ALL_DEVICES_READY = false;

    public static boolean NORTH_FOUND = false;
    public static boolean STAR_FOUND = false;



    /**
     * PI Network
     */
    public static String piIP;
    public static Socket PiSocket;
    public static PrintWriter piWriter;
    public static BufferedReader piReader;


    public static String phoneIP;
    public static String cameraIP;


    /**
     * Camera
     */
    public static boolean PRESS = false;
    public static void press(){
        PRESS = !PRESS;
    }
//    public static boolean PRESS2 = false;
//    public static void press2(){
//        PRESS2 = !PRESS2;
//    }

    /**
     * Phone
     *
     */
    public static ServerSocket cameraServerSocket;
    public static Socket cameraSocket;
    public static PrintWriter cameraWriter;
    public static BufferedReader cameraReader;


}
