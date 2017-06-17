package com.tinymos.demo.star_tracker;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhanghao on 2017/6/5.
 */

public class Constant {

    public static int port=4321;
    public static String serverIP = "10.12.44.104";    //Zhang Hao's iMac

//    static String serverIP = "10.12.87.140";  //Zhang Hao's Macbook Air

    public static String phoneIP;
    public static String cameraIP;
    public static String PiIP = "localhost";
    public static Socket PiSocket;
    public static PrintWriter piWriter;


    private static String deviceIpAddress;


    public static String getDeviceIpAddress() {
        return deviceIpAddress;
    }

    public static void setDeviceIpAddress(String deviceIpAddress) {
        Constant.deviceIpAddress = deviceIpAddress;
    }





}
