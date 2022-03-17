package com.ngoctin.intuitionmobile.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.apis.UserAPI;
import com.ngoctin.intuitionmobile.models.Address;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressService {

    public static void getListAddress(Activity activity, int id, EditText editTextAddress1, EditText editTextAddress2, EditText editTextAddress3) {
        String jwt = ApplicationUtils.getJwt(activity);
        UserAPI.userAPI
                .getAddress(jwt, id)
                .enqueue(new Callback<List<Address>>() {
                    @Override
                    public void onResponse(Call<List<Address>> call, Response<List<Address>> response) {
                        System.out.println("Address Activity!");
                        List<Address> list = response.body();
                        for (int i = 0; i < 3; i++) {
                            editTextAddress1.setText(list.indexOf(0));
                            editTextAddress2.setText(list.indexOf(1));
                            editTextAddress3.setText(list.indexOf(2));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Address>> call, Throwable t) {

                    }
                });
    }
}
