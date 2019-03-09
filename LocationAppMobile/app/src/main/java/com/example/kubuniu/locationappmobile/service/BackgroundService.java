package com.example.kubuniu.locationappmobile.service;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import com.example.kubuniu.locationappmobile.controller.LocationController;
import com.example.kubuniu.locationappmobile.data.Device;
import com.example.kubuniu.locationappmobile.data.Location;
import com.google.gson.Gson;

import java.time.LocalDateTime;

import static com.example.kubuniu.locationappmobile.config.StringHelper.PREFERENCES_NAME;
import static com.example.kubuniu.locationappmobile.config.StringHelper.PREFERENCES_TEXT_FIELD;

public class BackgroundService extends Service {

    private static Runnable runnable = null;
    private Context context = this;
    private Handler handler = null;
    private LocationController locationController;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        locationController = new LocationController(this);
        handler = new Handler();
//        runnable = () -> {
//            sendLocation();
//            handler.postDelayed(runnable, 10000);
//        };

        handler.postDelayed(runnable, 15000);
    }

    private void sendLocation() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        android.location.Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        Location location = new Location();
        location.setLat(lastKnownLocation.getLatitude());
        location.setLng(lastKnownLocation.getLongitude());
        location.setDateTime(LocalDateTime.now());
        location.setDevice(getDevice());

        locationController.sendLocation(location);
    }

    private Device getDevice() {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        final String deviceAsString = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        Gson gson = new Gson();
        return gson.fromJson(deviceAsString, Device.class);
    }

}