package com.example.kubuniu.locationappmobile.activitity;

import android.content.Intent;
import android.os.Bundle;
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
        this.deviceController = new DeviceController(this.getApplicationContext());
    }

    public void onLoginButtonClick(View v) {
        Device loginData = new Device();
        loginData.setLogin(((EditText) findViewById(R.id.login_emailid)).getText().toString());
        loginData.setPassword(((EditText) findViewById(R.id.login_password)).getText().toString());
        deviceController.login(loginData);
    }

    public void onRegisterSpanClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
