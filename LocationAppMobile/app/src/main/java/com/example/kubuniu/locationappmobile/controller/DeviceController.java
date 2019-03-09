package com.example.kubuniu.locationappmobile.controller;

import android.content.Context;
import android.widget.Toast;
import com.example.kubuniu.locationappmobile.data.Device;
import com.example.kubuniu.locationappmobile.service.DeviceService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeviceController {

    private final DeviceService deviceService;
    private final Retrofit retrofit;
    private final Context context;

    public DeviceController(Context context) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.9:8080")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        deviceService = retrofit.create(DeviceService.class);
        this.context = context;
    }


    public void login(final Device device) {
        Call<Device> call = deviceService.login(device);
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (response.isSuccessful()) {

                } else {
                    if (response.errorBody() != null) {
                        Toast.makeText(context, "User didn't exist",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Device> call, Throwable t) {
                Toast.makeText(context, "Cannot connect with server",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
