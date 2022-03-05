package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;

public class UpdateProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
//        String fullname = this.getIntent().getStringExtra("fullname").toString();
        SharedPreferences sharedPreferences = this.getSharedPreferences("user_store",this.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");
        AuthenticatedUser authenticatedUser = gson.fromJson(json,AuthenticatedUser.class);
        Toast.makeText(UpdateProfile.this, "This is Update Profile : " + authenticatedUser, Toast.LENGTH_LONG).show();
    }
}