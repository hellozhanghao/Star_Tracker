package com.tinymos.demo.star_tracker;

/**
 * Created by zhanghao on 2017/6/5.
 */

public class Constant {

    static int port=4321;
    static String serverIP = "10.12.44.104";    //Zhang Hao's iMac

//    static String serverIP = "10.12.87.140";  //Zhang Hao's Macbook Air

    static String phoneIP;
    static String cameraIP;


    private static String deviceIpAddress;


    public static String getDeviceIpAddress() {
        return deviceIpAddress;
    }

    public static void setDeviceIpAddress(String deviceIpAddress) {
        Constant.deviceIpAddress = deviceIpAddress;
    }





}
