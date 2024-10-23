package com.example.kishoreshop_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class ProductDetailsActivity extends AppCompatActivity {

    public String[] upozilaList = {"Dealer","Retailer","Fabricator"};

    String IMAGE_BASE_URL = "https://kishoreshop.com/storage/app/public/";
    private LinearLayout backButton;
    private TextView productNameTV;
    private TextView productPriceTV;
    private ImageView productIV;
    private TextView productId;
    private AutoCompleteTextView upozilaTV;

    ArrayAdapter<String> upozilaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_datails);

        backButton = findViewById(R.id.backButton);
        productNameTV = findViewById(R.id.productDetailsName);
        productPriceTV = findViewById(R.id.productDetailsPrice);
        productIV = findViewById(R.id.productDetailsIV);
        productId = findViewById(R.id.productId);
        upozilaTV = findViewById(R.id.upozilaTV);


        Intent intent = getIntent();
        String productIdStr = intent.getStringExtra("id");
        String productNameStr = intent.getStringExtra("name");
        String productPriceStr = intent.getStringExtra("price");
        String productFeaturesStr = intent.getStringExtra("features");
        String productImageStr = intent.getStringExtra("image");

        productNameTV.setText(productNameStr);
        productPriceTV.setText("Price: ৳"+productPriceStr);
        productId.setText(productFeaturesStr);
        Glide.with(this).load(IMAGE_BASE_URL+productImageStr)
                .error(R.drawable.ic_launcher_background)
                .into(productIV);

        backButton.setOnClickListener(view -> {
            finish();
        });

        upozilaAdapter = new ArrayAdapter<>(this, R.layout.item_upozila_name, upozilaList);
        upozilaTV.setAdapter(upozilaAdapter);


    }
}