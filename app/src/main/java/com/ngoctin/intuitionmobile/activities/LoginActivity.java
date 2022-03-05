package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.apis.AuthenticationAPI;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.services.AuthenticationService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText editTextUsername = findViewById(R.id.etUsername);
        EditText editTextPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        Button btnForwardSignup = this.findViewById(R.id.btnForwardSignUp);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                int check = AuthenticationService.validate(username,password);
                String message = "";
                switch (check){
                    case 0 :
                        AuthenticationService.login(username,password,LoginActivity.this);
                        break;
                    case 1 :
                        message = "Invalid Username !";
                        ApplicationUtils.clearEditTextWithToast(editTextUsername,message,LoginActivity.this);
                        break;
                    case 2 :
                        message = "Invalid Password";
                        ApplicationUtils.clearEditTextWithToast(editTextPassword,message,LoginActivity.this);
                        break;
                }
            }
        });
        btnForwardSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString();
                String password = editTextUsername.getText().toString();
                System.out.println("username" + username);
                System.out.println("password" + password);
                AuthenticationService.login(username,password,LoginActivity.this);
            }
        });

    }


}