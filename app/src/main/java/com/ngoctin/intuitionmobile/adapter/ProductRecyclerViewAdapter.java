package com.ngoctin.intuitionmobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.models.Product;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter {

    List<Product> products;
    Context context;

    public ProductRecyclerViewAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public ProductRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public ProductRecyclerViewAdapter() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();

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
        View productView = inflater.inflate(R.layout.product_item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(productView);
        // get product_item_layout
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Product product = products.get(position);

        ImageView productImage = holder.itemView.findViewById(R.id.productImage);
        Glide.
                with(holder.itemView.getContext())
                .load(product.getUrl())
                .centerCrop()
                .into(productImage);

        ((TextView)holder.itemView.findViewById(R.id.productName)).setText( "Name : " +  product.getName());
        ((TextView)holder.itemView.findViewById(R.id.productPrice)).setText("Price : " + product.getPrice());

    }

    @Override
    public int getItemCount() {
        if(products != null){
            return products.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
