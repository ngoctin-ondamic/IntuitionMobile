package com.ngoctin.intuitionmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.activities.ViewCartActivity;
import com.ngoctin.intuitionmobile.models.CartItem;
import com.ngoctin.intuitionmobile.services.CartService;

import java.util.ArrayList;
import java.util.List;

public class CartItemRecyclerViewAdapter extends RecyclerView.Adapter {

    List<CartItem> cart;
    Context context;
    TextView totalPrice;

    public CartItemRecyclerViewAdapter(List<CartItem> cart, Context context, TextView totalPrice) {
        this.cart = cart;
        this.context = context;
        this.totalPrice = totalPrice;
    }

    public TextView getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(TextView totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CartItemRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.cart_item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CartItem cartItem = cart.get(position);
        ImageView productImage = holder.itemView.findViewById(R.id.cartItemImage);
        System.out.println("onBindViewHolder - Image : " + cartItem.getProduct().getUrl());
        Glide.
                with(holder.itemView.getContext())
                .load(cartItem.getProduct().getUrl())
                .centerCrop()
                .into(productImage);
        System.out.println("onBindViewHolder : " + cartItem.toString());
        ((TextView)holder.itemView.findViewById(R.id.tvCartItemProductID)).setText(cartItem.getProduct().getId()+"");
        ((TextView)holder.itemView.findViewById(R.id.tvCartItemProductName)).setText(cartItem.getProduct().getName());
        TextView tvCartItemQuantity = holder.itemView.findViewById(R.id.tvCartItemQuantity);
        tvCartItemQuantity.setText(cartItem.getQuantity()+"");
        System.out.println("cartItem.getProduct().getPrice() : " +cartItem.getProduct().getPrice());
        System.out.println("tvCartItemQuantity.getText().toString() : " + tvCartItemQuantity.getText().toString());
        int cartItemPrice = Integer.parseInt(cartItem.getProduct().getPrice()) * (Integer.parseInt(tvCartItemQuantity.getText().toString()));
        TextView tvCartItemPrice = (TextView)holder.itemView.findViewById(R.id.tvCartItemPrice);
        tvCartItemPrice.setText(cartItemPrice + "");
        Button cartItemIncreaseBtn  = holder.itemView.findViewById(R.id.btnCartItemIncrease);
        Button cartItemDecreaseBtn  = holder.itemView.findViewById(R.id.btnCartItemDecrease);

        int tmp = position;

        cartItemIncreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currQuantity = Integer.parseInt(tvCartItemQuantity.getText().toString());
                currQuantity += 1;
                int price = Integer.parseInt(cartItem.getProduct().getPrice()) * currQuantity;
                tvCartItemPrice.setText(price + "");
                tvCartItemQuantity.setText(currQuantity + "");
                int cartTotalPrice = Integer.parseInt(totalPrice.getText().toString()) + Integer.parseInt(cartItem.getProduct().getPrice());
                totalPrice.setText(cartTotalPrice + "");
                List<CartItem> cartItems = CartService.getCart(getContext());
                CartService.updateCartItemQuantity(getContext(),cartItems,cartItem.getCartItemID(),1,0);
            }
        });
        cartItemDecreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currQuantity = Integer.parseInt(tvCartItemQuantity.getText().toString());
                currQuantity -= 1;
                if(currQuantity == 0){
                    cart.remove(tmp);
                    notifyItemRemoved(tmp);
                    notifyItemChanged(tmp, cart.size());
                    int cartTotalPrice = Integer.parseInt(totalPrice.getText().toString()) - Integer.parseInt(cartItem.getProduct().getPrice());
                    totalPrice.setText(cartTotalPrice + "");
                }else{
                    int price = Integer.parseInt(cartItem.getProduct().getPrice()) * currQuantity;
                    tvCartItemPrice.setText(price + "");
                    tvCartItemQuantity.setText(currQuantity + "");
                    int cartTotalPrice = Integer.parseInt(totalPrice.getText().toString()) - Integer.parseInt(cartItem.getProduct().getPrice());
                    totalPrice.setText(cartTotalPrice + "");
                    List<CartItem> cartItems = CartService.getCart(getContext());
                    CartService.updateCartItemQuantity(getContext(),cartItems,cartItem.getCartItemID(),1,1);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(cart != null){
            return cart.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        Button btnDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
