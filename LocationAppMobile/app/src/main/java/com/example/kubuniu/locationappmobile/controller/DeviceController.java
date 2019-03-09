package com.example.kubuniu.locationappmobile.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;
import com.example.kubuniu.locationappmobile.activitity.LoginActivity;
import com.example.kubuniu.locationappmobile.config.StringHelper;
import com.example.kubuniu.locationappmobile.data.Device;
import com.example.kubuniu.locationappmobile.service.BackgroundService;
import com.example.kubuniu.locationappmobile.service.DeviceService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.kubuniu.locationappmobile.config.StringHelper.PREFERENCES_NAME;
import static com.example.kubuniu.locationappmobile.config.StringHelper.PREFERENCES_TEXT_FIELD;

public class DeviceController {

    private final DeviceService deviceService;
    private final Retrofit retrofit;
    private final Context context;

    public DeviceController(Context context) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(StringHelper.URL)
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
                    saveContext(response);
                    context.startService(new Intent(context, BackgroundService.class));
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

    private void saveContext(Response<Device> response) {
        final SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        Gson gson = new Gson();
        preferencesEditor.putString(PREFERENCES_TEXT_FIELD, gson.toJson(response.body()));
        preferencesEditor.apply();
    }

    public void register(Device device) {
        Call<Device> call = deviceService.register(device);
        call.enqueue(new Callback<Device>() {
            @Override
            public void onResponse(Call<Device> call, Response<Device> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                } else {
                    if (response.errorBody() != null) {
                        Toast.makeText(context, "User already exist",
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
