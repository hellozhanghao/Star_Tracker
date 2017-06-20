package com.tinymos.demo.star_tracker.camera;

import android.app.Activity;
import android.content.Intent;
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
import java.net.ServerSocket;
import java.net.Socket;

public class StartCameraActivity extends Activity {

    // TODO: 2017/5/22 Add Camera UI


    private ServerSocket serverSocket;


    private Handler handler = new Handler();


    private Runnable backGroundTask = new Runnable() {

        public void run() {
            getMessages();

            handler.postDelayed(this, 2000); // refresh every 1000 ms = 1 sec
        }
    };

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
            serverSocket = new ServerSocket(4321);
            serverSocket.setSoTimeout(100);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Socket clientSocket = new Socket(Constant.serverIP, Constant.serverPort);
            PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true); //set true for autoflush
            printWriter.println("I am a Camera");

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


    public void getMessages(){

        try {
            Socket client = serverSocket.accept();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String message = bufferedReader.readLine();

            TextView text = (TextView) findViewById(R.id.viewer);
            text.setText(message);

            client.close();
//            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void runCamera(View view)
    {
        Intent intent = new Intent(StartCameraActivity.this, CameraActivity.class);
        startActivity(intent);
    }



}
