package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;

public class UpdateAddressActivity extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_address);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateAddressActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = this.getSharedPreferences("user_store",this.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("authenticated_user","");


    }
}