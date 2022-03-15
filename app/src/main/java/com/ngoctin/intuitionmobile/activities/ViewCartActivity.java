package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.CartItemRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ViewCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        List<CartItem> cart = new ArrayList<>();
        Product product = new Product(1,"Product 01","100",10,null,"https://cdn-images.italist.com/image/upload/t_zoom_v3_q_auto/209c197b638ccd99d013e10997e56f39.jpg");
        for (int i = 0; i < 10; i++) {
            cart.add(new CartItem(1,product,1));
        }
        TextView tvCartTotalPrice = findViewById(R.id.tvCartTotalPrice);
        int totalPrice = 0;
        for (CartItem cartItem:
             cart) {
            totalPrice += (Integer.parseInt(cartItem.getProduct().getPrice()) * cartItem.getQuantity());
        }
        tvCartTotalPrice.setText(totalPrice + "");
        CartItemRecyclerViewAdapter adapter = new CartItemRecyclerViewAdapter(cart,this,tvCartTotalPrice);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView cartRecyclerView = findViewById(R.id.rvCart);
        cartRecyclerView.setAdapter(adapter);
        cartRecyclerView.setLayoutManager(linearLayoutManager);

    }
}