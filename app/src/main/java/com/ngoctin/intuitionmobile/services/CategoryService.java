package com.ngoctin.intuitionmobile.services;

import android.content.Context;
import android.widget.Toast;

import com.ngoctin.intuitionmobile.activities.DemoActivity;
import com.ngoctin.intuitionmobile.apis.AuthenticationAPI;
import com.ngoctin.intuitionmobile.apis.CategoryAPI;
import com.ngoctin.intuitionmobile.models.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryService {

    public static void  searchCategoryByName(String searchValue, Context context){
        AuthenticationAPI.authenticationAPI.getJasonWebToken("ngoctin.user01", "123456Tin@")
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
//                                Toast.makeText(DemoActivity.this, "JWT : " + response.body(), Toast.LENGTH_SHORT).show();
                        // response.body = jwt
                        // gan duoc jwt vao cai header
                        String jwt = "Bearer " + response.body();
                        CategoryAPI.categoryApi.searchCategories(jwt,searchValue)
                                .enqueue(new Callback<List<Category>>() {
                                    @Override
                                    public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                                        List<Category> list = response.body();
                                        System.out.println("Response : " + list);
                                        Toast.makeText(context, "Response : " + list, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<List<Category>> call, Throwable t) {

                                    }
                                });

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
    }

}
