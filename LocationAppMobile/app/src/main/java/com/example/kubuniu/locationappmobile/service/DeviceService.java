package com.example.kubuniu.locationappmobile.service;

import com.example.kubuniu.locationappmobile.data.Device;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DeviceService {

    @POST("/login")
    Call<Device> login(@Body Device loginData);
}
