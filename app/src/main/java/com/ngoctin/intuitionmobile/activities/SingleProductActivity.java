package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.CartItemRecyclerViewAdapter;
import com.ngoctin.intuitionmobile.apis.ProductAPI;
import com.ngoctin.intuitionmobile.models.AuthenticatedUser;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.models.Product;
import com.ngoctin.intuitionmobile.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductActivity extends AppCompatActivity {

    List<CartItem> cart = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        ImageView productImage = findViewById(R.id.singleProductImage);
        TextView  productName = findViewById(R.id.singleProductName);
        TextView  productPrice = findViewById(R.id.singleProductPrice);
        TextView  productQuantity = findViewById(R.id.singleProductAvailable);
        TextView productDesc = findViewById(R.id.singleProductDescription);
        CartItemRecyclerViewAdapter cartItemRecyclerViewAdapter = new CartItemRecyclerViewAdapter(SingleProductActivity.this);
        ProductService.getProductByID(
                SingleProductActivity.this,
                productImage,productName,productPrice,
                productQuantity,productDesc);
        Button addToCartButton = findViewById(R.id.btnAddToCart);
        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 cart = cartItemRecyclerViewAdapter.getCart();
                Product product = new Product(
                        productName.getText().toString(),
                        productPrice.getText().toString(),
                        productDesc.getText().toString(),
                        null
                );
                CartItem cartItem = new CartItem(1,product,1);
                 if(cart == null){
                     cart = new ArrayList<>();
                     cart.add(cartItem);
                     cartItemRecyclerViewAdapter.setCart(cart);
                 }else{
                     cart.add(cartItem);
                     cartItemRecyclerViewAdapter.setCart(cart);
                 }
                cartItemRecyclerViewAdapter.notifyItemInserted(cart.size());
                cartItemRecyclerViewAdapter.notifyDataSetChanged();
            }
        });
    }
}