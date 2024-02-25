package com.example.practice3;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectedProductAdapter extends RecyclerView.Adapter<SelectedProductAdapter.ViewHolder> {

    private ArrayList<Product> products;


    public SelectedProductAdapter(ArrayList<Product> products) {this.products=products;}

    @NonNull
    @Override
    public SelectedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectedProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);

        holder.name.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.price.setText(product.getPrice());
        holder.seller.setText(product.getSeller());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,description,price,seller;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameText);
            description = itemView.findViewById(R.id.descriptionText);
            price = itemView.findViewById(R.id.priceText);
            seller = itemView.findViewById(R.id.sellerText);


        }
    }


}
