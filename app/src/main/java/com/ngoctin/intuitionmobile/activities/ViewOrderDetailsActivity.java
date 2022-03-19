package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.adapter.OrderDetailsAdapter;
import com.ngoctin.intuitionmobile.services.OrderService;
import com.ngoctin.intuitionmobile.utils.ApplicationUtils;

public class ViewOrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order_details);
        Long orderID = Long.parseLong(getIntent().getStringExtra("order_id"));
        Toast.makeText(this, "orderID : " + orderID, Toast.LENGTH_SHORT).show();
        RecyclerView rvOrderDetails = this.findViewById(R.id.rvOrderDetails);
        OrderDetailsAdapter adapter = new OrderDetailsAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvOrderDetails.setLayoutManager(linearLayoutManager);
        TextView tvOrderDetailOrderID = this.findViewById(R.id.tvOrderDetailOrderID);
        tvOrderDetailOrderID.setText(orderID+"");
        OrderService.
                getOrderDetailsByOrderID(this,
                        ApplicationUtils.getJwt(this),
                        orderID,
                        rvOrderDetails,
                        adapter);

        ImageView btnBack = this.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewOrderDetailsActivity.this, OrderHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}