package com.tinymos.demo.star_tracker;

import android.location.LocationListener;
import android.location.LocationManager;

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

    //Review 3



    /**
     * Global Boolean Variables
     */

    public static boolean NORTH_FOUND = false;
    public static boolean STAR_FOUND = false;
    public static boolean PICTURE_TAKEN = false;



    /**
     * PI Network
     */
    public static String piIP = "10.12.67.233";
    public static Socket PiSocket;
    public static PrintWriter piWriter;
    public static BufferedReader piReader;


    public static String phoneIP = "10.12.100.41";
    public static String cameraIP = "10.12.152.117";


    /**
     * Camera
     */
    public static boolean PRESS = false;
    public static void press(){
        PRESS = !PRESS;
    }

    public static boolean cameraPhoneConnected = false;





    /**
     * Phone
     *
     */
    public static ServerSocket cameraServerSocket;
    public static Socket cameraSocket;
    public static PrintWriter cameraWriter;
    public static BufferedReader cameraReader;

    // GPS
    public static LocationManager locationManager;
    public static LocationListener locationListener;



}
