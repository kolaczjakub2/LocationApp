package com.example.kubuniu.locationappmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.kubuniu.locationappmobile.controller.DeviceController;


public class LoginActivity extends AppCompatActivity {

    private DeviceController deviceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.deviceController = new DeviceController(this.getApplicationContext());
    }

    public void onLoginButtonClick(View v) {
        LoginData loginData = new LoginData();
        loginData.setLogin(((EditText) findViewById(R.id.login_emailid)).getText().toString());
        loginData.setPassword(((EditText) findViewById(R.id.login_password)).getText().toString());
        deviceController.login(loginData);
    }

}
