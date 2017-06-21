package com.tinymos.demo.star_tracker;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/5.
 */

public class Global {




//    NORTH FOUND
//    STAR FOUND
//    TRACKER READY
//    PICTURE TAKEN



    //server communications
    public static int serverPort =4321;
//    public static String serverIP = "10.12.44.104";    //Zhang Hao's iMac
    public static String serverIP = "10.12.87.140";

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

    public static boolean devicesReady = false;



//    static String serverIP = "10.12.87.140";  //Zhang Hao's Macbook Air

    public static String phoneIP;
    public static String cameraIP;
    public static String piIP;
    public static Socket PiSocket;
    public static PrintWriter piWriter;
    public static BufferedReader piReader;


    private static String deviceIpAddress;


    public static String getDeviceIpAddress() {
        return deviceIpAddress;
    }

    public static void setDeviceIpAddress(String deviceIpAddress) {
        Global.deviceIpAddress = deviceIpAddress;
    }





}
