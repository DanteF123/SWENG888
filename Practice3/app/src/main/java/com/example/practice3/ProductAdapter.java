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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> products;
    boolean isSelectMode = false;
    ArrayList<Product> selectedItems = new ArrayList<>();

    public ProductAdapter(List<Product> products) {this.products=products;}

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
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

            //begin new code
            itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    isSelectMode = true;
                    if(selectedItems.contains(products.get(getAdapterPosition()))){
                        itemView.setBackgroundColor(Color.TRANSPARENT);
                        selectedItems.remove(products.get(getAdapterPosition()));
                    }else{
                        itemView.setBackgroundColor(Color.GREEN);
                        selectedItems.add(products.get(getAdapterPosition()));
                    }
                    if (selectedItems.size()==0)
                        isSelectMode=false;
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isSelectMode){
                        if (selectedItems.contains(products.get(getAdapterPosition()))){
                            itemView.setBackgroundColor(Color.TRANSPARENT);
                            selectedItems.remove(products.get(getAdapterPosition()));
                        }
                        else{
                            itemView.setBackgroundColor(Color.GREEN);
                            selectedItems.add(products.get(getAdapterPosition()));
                        }
                        if(selectedItems.size()==0){
                            isSelectMode=false;
                        }
                    }
                    else{

                    }
                }
            });

        }
    }


}
