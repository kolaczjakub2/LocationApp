package com.example.kubuniu.locationappmobile.activitity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.kubuniu.locationappmobile.R;
import com.example.kubuniu.locationappmobile.controller.DeviceController;
import com.example.kubuniu.locationappmobile.data.Device;

public class RegisterActivity extends AppCompatActivity {

    private DeviceController deviceController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.deviceController = new DeviceController(this.getApplicationContext(), this);
    }

    public void onRegisterButtonClick(View view) {
        String login = ((EditText) findViewById(R.id.login_emailid)).getText().toString();
        String password = ((EditText) findViewById(R.id.login_password)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.confirm_password)).getText().toString();

        if (validateRegisterData(login, password, confirmPassword)) {
            Device loginData = new Device();
            loginData.setLogin(((EditText) findViewById(R.id.login_emailid)).getText().toString());
            loginData.setPassword(password);
            deviceController.register(loginData);
        }


    }

    public Boolean validateRegisterData(final String login, final String password, final String confirmPassword) {
        if (password.length() < 8) {
            Toast.makeText(this.getApplicationContext(), "Password is too short",
                    Toast.LENGTH_LONG).show();
            return false;
        }
        if (!password.equals(confirmPassword)) {
            Toast.makeText(this.getApplicationContext(), "Confirm password is not equal to password",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        if (login.length() < 8) {
            Toast.makeText(this.getApplicationContext(), "Login is too short",
                    Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}
