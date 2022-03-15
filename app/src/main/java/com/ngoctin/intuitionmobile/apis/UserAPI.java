package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.Address;
import com.ngoctin.intuitionmobile.models.InforToUpdate;
import com.ngoctin.intuitionmobile.models.UpdateUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserAPI {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    UserAPI userAPI = new Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080/api/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI.class);

    @GET("getUserInfo")
    Call<UpdateUser> getUserInfo(@Header("Authorization") String jwt);


    @PUT("update/{name}")
    Call<InforToUpdate> updateUser(@Header("Authorization") String jwt,
                                   @Path("name") String username,
                                   @Body InforToUpdate userInfo );

    @GET("/getAddress/{id}")
    Call<List<Address>> getAddress(@Header("Authorization") String jwt,
                                   @Path("id") Long id);
}
