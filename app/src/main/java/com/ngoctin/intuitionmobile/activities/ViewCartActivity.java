package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.CartItemRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Product;
import com.ngoctin.intuitionmobile.services.CartService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        List<CartItem> cart = CartService.getCart(this);
        TextView tvCartTotalPrice = findViewById(R.id.tvCartTotalPrice);
        int totalPrice = CartService.getCartTotal(cart);
        tvCartTotalPrice.setText(totalPrice + "");
        CartItemRecyclerViewAdapter adapter = new CartItemRecyclerViewAdapter(cart,this,tvCartTotalPrice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView cartRecyclerView = findViewById(R.id.rvCart);
        cartRecyclerView.setAdapter(adapter);
        cartRecyclerView.setLayoutManager(linearLayoutManager);
    }
}