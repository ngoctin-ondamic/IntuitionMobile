package com.ngoctin.intuitionmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngoctin.intuitionmobile.R;
import com.ngoctin.intuitionmobile.apis.AuthenticationAPI;
import com.ngoctin.intuitionmobile.apis.CategoryAPI;
import com.ngoctin.intuitionmobile.models.Category;
import com.ngoctin.intuitionmobile.services.CategoryService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        EditText editTextSearchValue = this.findViewById(R.id.etSearchCategoryByLikeName);
        Button btnSearchCategory = this.findViewById(R.id.btnSearchCategoryByLikeName);
        btnSearchCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchValue = editTextSearchValue.getText().toString();
                CategoryService.searchCategoryByName(searchValue,DemoActivity.this);
            }
        });


    }
}