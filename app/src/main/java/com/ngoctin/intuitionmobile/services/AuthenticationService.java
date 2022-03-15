package com.ngoctin.intuitionmobile.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.activities.LoginActivity;
import com.ngoctin.intuitionmobile.activities.LoginFailedActivity;
import com.ngoctin.intuitionmobile.activities.UserHomeScreenActivity;
import com.ngoctin.intuitionmobile.apis.AuthenticationAPI;
import com.ngoctin.intuitionmobile.models.Address;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Product;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationService {

    public static void login(String username, String password, Context context) {
        AuthenticationAPI.authenticationAPI.getJasonWebToken(username, password)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String jwt = "Bearer " + response.body();
                        AuthenticationAPI.authenticationAPI.getAuthenticatedUser(jwt)
                                .enqueue(new Callback<AuthenticatedUser>() {
                                    @Override
                                    public void onResponse(Call<AuthenticatedUser> call, Response<AuthenticatedUser> response) {
                                        try {
                                            if(response.body() != null
                                                    && response.code() == 200
                                                    && response.body().getRole().equalsIgnoreCase("user")){
                                                Intent intent = new Intent(context, UserHomeScreenActivity.class);
                                                intent.putExtra("jwt",jwt);
                                                intent.putExtra("fullname",response.body().getFullname());

                                                // After login
                                                // Create SharedPreference to save data [userInfor, Cart]

                                                AuthenticatedUser authenticatedUser = response.body();
                                                authenticatedUser.setJwt(jwt);
                                                SharedPreferences sp = context.getSharedPreferences("user_store",Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sp.edit();
                                                Gson gson = new Gson();
                                                String userInformation = gson.toJson(authenticatedUser);
                                                editor.putString("authenticated_user",userInformation);
                                                List<CartItem> cart = new ArrayList<>();
                                                String cartJson = gson.toJson(cart);
                                                editor.putString("cart",cartJson);
                                                editor.commit();

                                                context.startActivity(intent);
                                            }else{
                                                Toast.makeText(context, "Account [" + username + "] does not exist !", Toast.LENGTH_SHORT).show();
                                            }
                                        }catch (Exception exception){
                                            Toast.makeText(context, "Login Failed ( Catch ) !", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<AuthenticatedUser> call, Throwable t) {
                                        System.out.println("Exception : " + t.getMessage());
                                    }
                                });
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
    }
    public static int validate(String username, String password){
        if(username.length() == 0){
            return 1;
        }
        if(password.length() == 0){
            return 2;
        }
        return 0;
    }

}
