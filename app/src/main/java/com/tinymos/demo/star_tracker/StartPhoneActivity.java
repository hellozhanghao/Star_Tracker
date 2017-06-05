package com.tinymos.demo.star_tracker;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;

public class StartPhoneActivity extends Activity {


    public String getLocalIpAddress(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_phone);

        //get device address
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Constant.setDeviceIpAddress(ip);

        //to avoid network on main thread exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Socket clientSocket = new Socket(Constant.serverIP, Constant.port);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true); //set true for autoflush
            printWriter.println("I am a Phone");

            Toast.makeText(getApplication(),"IP address "+ip+" sent to server",
                    Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            String message = e.getMessage();
            Toast.makeText(getApplication(),message, Toast.LENGTH_LONG).show();
        }



    }




}
