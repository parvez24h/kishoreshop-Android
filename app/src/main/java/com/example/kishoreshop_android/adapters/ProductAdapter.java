package com.example.kishoreshop_android.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kishoreshop_android.ProductDetailsActivity;
import com.example.kishoreshop_android.R;
import com.example.kishoreshop_android.models.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    String IMAGE_BASE_URL = "https://kishoreshop.com/storage/app/public/";
    private Context context;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {

        Product product = products.get(position);

        holder.productNameTV.setText(product.getName());
        holder.productPriceTV.setText("৳"+product.getPrice());
        Glide.with(context).load(IMAGE_BASE_URL+product.getImage())
                .error(R.drawable.ic_launcher_background)
                .into(holder.productImageIV);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProductDetailsActivity.class);
            intent.putExtra("id",String.valueOf(product.getId()));
            intent.putExtra("name",product.getName());
            intent.putExtra("price",product.getPrice());
            intent.putExtra("features",product.getFeatures());
            intent.putExtra("image",product.getImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageIV;
        private TextView productNameTV;
        private TextView productPriceTV;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageIV = itemView.findViewById(R.id.productImageIV);
            productNameTV = itemView.findViewById(R.id.productNameTV);
            productPriceTV = itemView.findViewById(R.id.productPriceTV);
        }
    }
}
