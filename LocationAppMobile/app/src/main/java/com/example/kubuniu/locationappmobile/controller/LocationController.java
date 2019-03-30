package com.example.kubuniu.locationappmobile.controller;

import android.content.Context;
import android.widget.Toast;
import com.example.kubuniu.locationappmobile.config.StringHelper;
import com.example.kubuniu.locationappmobile.data.Location;
import com.example.kubuniu.locationappmobile.service.LocationService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.UUID;

public class LocationController {

    private final LocationService locationService;
    private final Retrofit retrofit;
    private final Context context;

    public LocationController(Context context) {
        retrofit = new Retrofit.Builder()
                .baseUrl(StringHelper.URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        locationService = retrofit.create(LocationService.class);
        this.context = context;
    }

    public void sendLocation(final Location location,final UUID deviceId) {
        System.out.println("XXX");
        Call<Location> call = locationService.sendLocation(location,deviceId);
        call.enqueue(new Callback<Location>() {
            @Override
            public void onResponse(Call<Location> call, Response<Location> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Your GeoLocation is send to server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Location> call, Throwable t) {
            }
        });
    }
}
