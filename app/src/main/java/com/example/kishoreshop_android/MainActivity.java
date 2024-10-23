package com.example.kishoreshop_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kishoreshop_android.adapters.ProductAdapter;
import com.example.kishoreshop_android.models.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "parvez";

    private static final String BASE_URL = "https://kishoreshop.com/api/products";
    private static final String FACEBOOK_URL = "https://www.facebook.com/kishoreshopdotcom";
    private static final String PHONE_NUMBER = "+8801736194336";
    private ImageView brandImage;
    private ImageButton callButton;
    private ImageButton facebookButton;

    private RecyclerView productsRV;

    private ProductAdapter productAdapter;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setClickListeners();



        Log.i(TAG, "onCreate: 1");
        RequestQueue queue = Volley.newRequestQueue(this);
        Log.i(TAG, "onCreate: 2");
        StringRequest request = new StringRequest(BASE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i(TAG, "onResponse: 3");

                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();
                    Product[] models = gson.fromJson(response, Product[].class);
                    Type mapType = new TypeToken<Map<String, String>>() {}.getType();

                    String text = "";
                    String feature = "\n";
                    products.clear();
                    for (int i = 0; i<models.length; i++){
                        Map<String,String> features = gson.fromJson(models[i].getFeatures(),mapType);
                        for (Map.Entry<String,String> entry: features.entrySet()){
                            feature = feature + " ->  "+
                                    entry.getKey()+" : "+entry.getValue()+"\n";
                        }
                        products.add(models[i]);
                        text= text +
                                "ID: "+models[i].getId() + "\n" +
                                "Name: "+models[i].getName() + "\n"+
                                "Image: "+models[i].getImage() + "\n"+
                                "Price: "+models[i].getPrice() + "\n"+
                                "Video: "+models[i].getVideo() + "\n"+
                                "Feature: "+feature + "\n"+
                                "Priority: "+models[i].getPriority() + "\n\n";

                        feature= "\n";

                    }

                    productAdapter.notifyDataSetChanged();
                    Log.i(TAG, "onResponse: 31: "+text);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "Error: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, "onErrorResponse: 4");
                Toast.makeText(MainActivity.this, "Error: Response Not Found", Toast.LENGTH_SHORT).show();

            }
        });
        Log.i(TAG, "onCreate: 5");
        queue.add(request);

        Log.i(TAG, "onCreate: 6");


    }

    private void initViews() {
        brandImage = findViewById(R.id.brandImage);
        callButton = findViewById(R.id.callButton);
        facebookButton = findViewById(R.id.facebookButton);

        productsRV = findViewById(R.id.productsRV);
        productsRV.setLayoutManager(new GridLayoutManager(this,2));

        products = new ArrayList<>();
        productAdapter = new ProductAdapter(this,products);
        productsRV.setAdapter(productAdapter);

    }

    private void setClickListeners() {
        brandImage.setOnClickListener(brandImageListener);
        callButton.setOnClickListener(callButtonListener);
        facebookButton.setOnClickListener(facebookButtonListener);


    }

    View.OnClickListener brandImageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
    View.OnClickListener facebookButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK_URL));
            startActivity(browserIntent);
        }
    };

    View.OnClickListener callButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL);
            dialIntent.setData(Uri.parse("tel:" + PHONE_NUMBER));
            startActivity(dialIntent);
        }
    };


}