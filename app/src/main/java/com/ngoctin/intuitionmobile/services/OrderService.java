package com.ngoctin.intuitionmobile.services;

import android.content.Context;
import android.content.Intent;

import com.ngoctin.intuitionmobile.activities.OrderSuccessfullyActivity;
import com.ngoctin.intuitionmobile.apis.OrderAPI;
import com.ngoctin.intuitionmobile.models.Order;
import com.ngoctin.intuitionmobile.models.OrderDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderService {

    public static void createOrder(Context context,String jwt ,Order order, List<OrderDetail> orderDetailList){
        OrderAPI.orderApi
                .createOrder(jwt,order)
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        System.out.println("createOrder : " + response.body() + " : message : " + response.message());
                        if(response.code() == 200 && response.body() == true){
                            for (OrderDetail orderDetail: orderDetailList) {
                                System.out.println("orderDetail.getProductID()" + orderDetail.getProductID());
                                System.out.println("orderDetail.getOrderID()" + orderDetail.getOrderID());
                            }
                            OrderAPI.orderApi
                                    .createOrderDetails(jwt,orderDetailList)
                                    .enqueue(new Callback<Boolean>() {
                                        @Override
                                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                                            System.out.println("createOrderDetails");
                                            Intent intent = new Intent(context, OrderSuccessfullyActivity.class);
                                            context.startActivity(intent);
                                        }

                                        @Override
                                        public void onFailure(Call<Boolean> call, Throwable t) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
    }

}
