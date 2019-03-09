package com.example.kubuniu.locationappmobile.service;

import com.example.kubuniu.locationappmobile.data.Location;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LocationService {

    @POST("/location/add")
    Call<Location> sendLocation(@Body Location location);
}
