package com.ngoctin.intuitionmobile.services;

import androidx.recyclerview.widget.RecyclerView;

import com.ngoctin.intuitionmobile.adapter.ProductRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.apis.ProductAPI;
import com.ngoctin.intuitionmobile.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductService {

    public static void getProducts(String jwt,  RecyclerView recyclerView, ProductRecyclerViewAdapter productRecyclerViewAdapter, int cateID) {
        switch (cateID){
            case 0 :
                ProductAPI.productApi
                        .getAllProducts(jwt)
                        .enqueue(new Callback<List<Product>>() {
                            @Override
                            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                productRecyclerViewAdapter.setProducts(response.body());
                                recyclerView.setAdapter(productRecyclerViewAdapter);
                            }

                            @Override
                            public void onFailure(Call<List<Product>> call, Throwable t) {

                            }
                        });
                break;
            case 1 :
                ProductAPI.productApi
                        .getProductsByCateID(jwt,1)
                        .enqueue(new Callback<List<Product>>() {
                            @Override
                            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                productRecyclerViewAdapter.setProducts(response.body());
                                recyclerView.setAdapter(productRecyclerViewAdapter);
                            }

                            @Override
                            public void onFailure(Call<List<Product>> call, Throwable t) {

                            }
                        });
                break;
            case 2 :
                ProductAPI.productApi
                        .getProductsByCateID(jwt,2)
                        .enqueue(new Callback<List<Product>>() {
                            @Override
                            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                                productRecyclerViewAdapter.setProducts(response.body());
                                recyclerView.setAdapter(productRecyclerViewAdapter);
                            }

                            @Override
                            public void onFailure(Call<List<Product>> call, Throwable t) {

                            }
                        });
                break;
            default:break;
        }
    }

}
