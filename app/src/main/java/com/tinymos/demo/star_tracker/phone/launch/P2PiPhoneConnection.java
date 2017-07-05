package com.tinymos.demo.star_tracker.phone.launch;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tinymos.demo.star_tracker.Global;
import com.tinymos.demo.star_tracker.R;
import com.tinymos.demo.star_tracker.phone.P3Calibrating;

import java.util.List;

public class P2PiPhoneConnection extends Activity {


    private Handler handler = new Handler();

    private Runnable backGroundTask = new Runnable() {

        public void run() {
            if (Global.piWriter != null) {
                onCalibrateRequested();
            } else {
                Log.d(this.toString(), "Not ready");
                handler.postDelayed(this, 2000);
            }
        }
    };



//    LocationManager mLocationManager;
//    Location myLocation = getLastKnownLocation();
//
//    private Location getLastKnownLocation() {
//        mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
//        List<String> providers = mLocationManager.getProviders(true);
//        mLocationManager.requestLocationUpdates(mLocationManager.GPS_PROVIDER,);
//        Location bestLocation = null;
//        for (String provider : providers) {
//            Location l = mLocationManager.getLastKnownLocation(provider);
//            if (l == null) {
//                continue;
//            }
//            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
//                // Found best last known location: %s", l);
//                bestLocation = l;
//            }
//        }
//        return bestLocation;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_loading);

        TextView text = (TextView) findViewById(R.id.text);
        text.setText("Connecting to Tracker");

//        //to avoid network on main thread exception
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);


        Thread phoneToPiThread = new PhoneToPiConnectionThread();
        phoneToPiThread.start();

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
//                makeUseOfNewLocation(location);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i("location","pers");
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null){
            locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
            location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }

        Log.d("location",String.valueOf(location.getLatitude()));
        Log.d("location",String.valueOf(location.getLongitude()));

//        Toast.makeText(getApplication(),location, Toast.LENGTH_LONG).show();

        backGroundTask.run();


    }

    @Override
    public void onBackPressed() {
        //disable backbutton
    }

    public void onCalibrateButtonClicked(View view)
    {

        if (Global.piWriter != null){
            Intent intent = new Intent(P2PiPhoneConnection.this, P3Calibrating.class);
            Global.piWriter.println("CALIBRATE TRUE");
            startActivity(intent);
        }else {
            Toast.makeText(getApplication(),"Tracker not ready", Toast.LENGTH_LONG).show();
        }
    }

    public void onCalibrateRequested()
    {
            Intent intent = new Intent(P2PiPhoneConnection.this, P3Calibrating.class);
            Global.piWriter.println("CALIBRATE TRUE");
            startActivity(intent);
    }

    public void onCaptureButtonClicked(View view){
        if (Global.cameraWriter!=null){
            Global.cameraWriter.println("press");
        }else {
            Toast.makeText(getApplication(),"Camera not ready", Toast.LENGTH_LONG).show();

        }
    }

}
