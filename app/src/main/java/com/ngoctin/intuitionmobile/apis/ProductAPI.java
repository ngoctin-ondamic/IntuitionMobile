package com.ngoctin.intuitionmobile.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ngoctin.intuitionmobile.models.Category;
import com.ngoctin.intuitionmobile.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ProductAPI {

    String localhost = "192.168.1.14";
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ProductAPI productApi = new Retrofit.Builder()
            .baseUrl("http://" + localhost + ":8080/api/product/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ProductAPI.class);

    @GET("getAllProducts")
    Call<List<Product>> getAllProducts(
            @Header("Authorization") String jwt);

    @GET("getProducts/{cateID}")
    Call<List<Product>> getProductsByCateID(
            @Header("Authorization") String jwt,
            @Path("cateID") int cateID);


}
