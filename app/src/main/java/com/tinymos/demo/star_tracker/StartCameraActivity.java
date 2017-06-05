package com.tinymos.demo.star_tracker;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class StartCameraActivity extends Activity {

    // TODO: 2017/5/22 Add Camera UI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_camera);

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
            printWriter.println("I am a Camera");

            Toast.makeText(getApplication(),"IP address "+ip+" sent to server",
                    Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
            String message = e.getMessage();
            Toast.makeText(getApplication(),message, Toast.LENGTH_LONG).show();

        }
    }
}
