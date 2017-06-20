package com.tinymos.demo.star_tracker.phone;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.Constant;
import com.tinymos.demo.star_tracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StartPhoneActivity extends Activity {


//    long time = System.currentTimeMillis();

    private int counter = 0;

    private Handler handler = new Handler();

    private Runnable backGroundTask = new Runnable() {

        public void run() {

            handler.postDelayed(this, 1000); // refresh every 1000 ms = 1 sec
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone_start_phone);

        //get device address
        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Constant.setDeviceIpAddress(ip);

        //to avoid network on main thread exception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Socket clientSocket = new Socket(Constant.serverIP, Constant.serverPort);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true); //set true for autoflush
            printWriter.println("I am a Phone");

            Toast.makeText(getApplication(),"IP address "+ip+" sent to server",
                    Toast.LENGTH_LONG).show();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
            String message = e.getMessage();
            Toast.makeText(getApplication(),message, Toast.LENGTH_LONG).show();
        }

        backGroundTask.run();
    }

    public void getIP(View view){
        try {
            Socket clientSocket = new Socket(Constant.serverIP, Constant.serverPort);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true); //set true for autoflush
            printWriter.println("request ip");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message = bufferedReader.readLine();
            clientSocket.close();
            TextView text = (TextView) findViewById(R.id.mainText);
            text.setText(message);

            String[] messages = message.split(";");
            String[] messages1 = messages[0].split(":");
            String[] messages2 = messages[1].split(":");
            Constant.phoneIP = messages1[1];
            Constant.cameraIP = messages2[1];

        } catch (IOException e) {
            e.printStackTrace();
            String message = e.getMessage();
            Toast.makeText(getApplication(),message, Toast.LENGTH_LONG).show();
        }
    }

    public void sendMessage(View view){
        try {
            Socket clientSocket = new Socket(Constant.cameraIP, Constant.serverPort);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true); //set true for autoflush
            printWriter.println("Message "+ counter);
            counter++;

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
            String message = e.getMessage();
            Toast.makeText(getApplication(),message, Toast.LENGTH_LONG).show();
        }
    }




}
