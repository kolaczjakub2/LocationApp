package com.example.kubuniu.locationappmobile.service;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import com.example.kubuniu.locationappmobile.controller.LocationController;
import com.example.kubuniu.locationappmobile.data.Device;
import com.example.kubuniu.locationappmobile.data.Location;
import com.google.gson.Gson;

import java.time.LocalDateTime;

import static com.example.kubuniu.locationappmobile.config.StringHelper.PREFERENCES_NAME;
import static com.example.kubuniu.locationappmobile.config.StringHelper.PREFERENCES_TEXT_FIELD;

public class BackgroundService extends Service {

    private Context context = this;
    private LocationController locationController;
    LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(android.location.Location location) {
            sendLocation(location);
            System.out.println("ZZZ");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    private LocationManager mLocationManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Invoke background service onCreate method.", Toast.LENGTH_LONG).show();
        locationController = new LocationController(this);
        System.out.println("ZZZ");

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

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
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListenerGPS);

    }

    private void sendLocation(android.location.Location aLocation) {
        Location location = new Location();
        location.setLat(aLocation.getLatitude());
        location.setLng(aLocation.getLongitude());
        location.setDateTime(LocalDateTime.now());

        locationController.sendLocation(location, getDevice().getId());
    }

    private Device getDevice() {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        final String deviceAsString = preferences.getString(PREFERENCES_TEXT_FIELD, "");
        Gson gson = new Gson();
        return gson.fromJson(deviceAsString, Device.class);
    }
}