package com.example.kubuniu.locationappmobile.activitity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.kubuniu.locationappmobile.R;
import com.example.kubuniu.locationappmobile.controller.DeviceController;
import com.example.kubuniu.locationappmobile.data.Device;


public class LoginActivity extends AppCompatActivity {

    private DeviceController deviceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.deviceController = new DeviceController(this.getApplicationContext(),this);
    }

    public void onLoginButtonClick(View v) {
        Device loginData = new Device();
        loginData.setLogin(((EditText) findViewById(R.id.login_emailid)).getText().toString());
        loginData.setPassword(((EditText) findViewById(R.id.login_password)).getText().toString());
        deviceController.login(loginData);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION },0);
        }
    }

    public void onRegisterSpanClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
