package com.ngoctin.intuitionmobile.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.activities.AddressActivity;
import com.ngoctin.intuitionmobile.apis.UserAPI;
import com.ngoctin.intuitionmobile.models.Address;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressService {

    public static MutableLiveData<List<Address>> observableAddress = new MutableLiveData<>();
    public static void getAddress(String jwt, Long id, Context context) {
        UserAPI.userAPI.getAddress(jwt, id).enqueue(new Callback<List<Address>>() {
            @Override
            public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                if (response.body() != null
                                    && response.code() == 200) {

                    List<Address> userAddress = response.body();
//                    Intent intent = new Intent(context, AddressActivity.class);
                    SharedPreferences sharedpreferences = context.getSharedPreferences("user_address", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    Gson gson = new Gson();
                    String address = gson.toJson(userAddress);
                    editor.putString("addresses", address);
                    editor.commit();
                    System.out.println("Address Service!!");
//                    context.startActivity(intent);
////                    observableAddress.postValue(userAddress);
                } else  {
                    Toast.makeText(context, "List is Empty!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Address>> call, Throwable t) {
                System.out.println("Exception : " + t.getMessage());
            }
        });
    }
}
