package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.models.InforToUpdate;
import com.ngoctin.intuitionmobile.models.UpdateUser;
import com.ngoctin.intuitionmobile.services.UserService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.ArrayList;
import java.util.List;

public class UpdateProfile extends AppCompatActivity {

    private ImageView btnBack;
    private Button btnUpdate;
    private EditText editUsername;
    private EditText editFullname;
    private EditText editPhonenumber;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateProfile.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        AuthenticatedUser user = ApplicationUtils.getAuthenticatedUser(this);
        String jwt = ApplicationUtils.getJwt(this);
        editUsername = (EditText) findViewById(R.id.etUsername);
        editFullname = (EditText) findViewById(R.id.etFullname);
        editPhonenumber = (EditText) findViewById(R.id.etPhoneNumber);
        editEmail = (EditText) findViewById(R.id.etEmail);
        editUsername.setText(user.getUsername());
        editUsername.setFocusable(false);
        editFullname.setText(user.getFullname());
        editPhonenumber.setText(user.getPhoneNumber());
        editEmail.setText(user.getEmail());

//
//        Toast.makeText(UpdateProfile.this, "user : " + user.getPassword(), Toast.LENGTH_SHORT).show();
        List<EditText> editTextList = new ArrayList<>();
        editTextList.add(editFullname);
        editTextList.add(editPhonenumber);
        editTextList.add(editEmail);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = editFullname.getText().toString();
                String phonenumber = editPhonenumber.getText().toString();
                String email = editEmail.getText().toString();
                String username = user.getUsername();
                InforToUpdate infoUser = new InforToUpdate(fullname, phonenumber, email);
                int validateInput = UserService.validate(infoUser);
                String message = "Update Successfully!";
                System.out.println("validateInput : " + validateInput);
                switch (validateInput) {
                    case 0:
                        System.out.println("onClick : " + message);
                        UserService.update(jwt, username ,message, infoUser, UpdateProfile.this);
                        ApplicationUtils.clearAllEditTexts(editTextList);
                        break;
                    case 1:
                        message = "Full name can only contain letters ! ";
                        ApplicationUtils.clearEditTextWithToast(editFullname, message, UpdateProfile.this);
                        break;
                    case 2:
                        message = "Phone Number must be from 10 - 12 numbers !";
                        ApplicationUtils.clearEditTextWithToast(editPhonenumber, message, UpdateProfile.this);
                        break;
                    case 3:
                        message = "Invalid Email !";
                        ApplicationUtils.clearEditTextWithToast(editEmail, message, UpdateProfile.this);
                        break;
                }
            }
        });
    }
}