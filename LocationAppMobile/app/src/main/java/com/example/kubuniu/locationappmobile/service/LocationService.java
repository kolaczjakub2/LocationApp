package com.example.kubuniu.locationappmobile.service;

import com.example.kubuniu.locationappmobile.data.Location;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.UUID;

public interface LocationService {

    @POST("/location/{deviceId}/add")
    Call<Location> sendLocation(@Body Location location, @Path("deviceId") UUID deviceId);
}
