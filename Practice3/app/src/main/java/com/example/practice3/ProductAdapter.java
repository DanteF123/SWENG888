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

    // Main list of product items to be displayed by main activity
    private List<Product> products;
    //boolean to capture if each individual item has been selected or not.
    boolean isSelectMode = false;
    // Array list of selected items to be sent to Results activity.
    ArrayList<Product> selectedItems = new ArrayList<>();

    public ProductAdapter(List<Product> products) {this.products=products;}

    //creating the new viewholder
    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    //binding data to the viewholder
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

    //Viewholder class used to declare and assign data attributes that are used in the Recycler View
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,description,price,seller;

        public ViewHolder(View itemView) {
            super(itemView);

            // assigning attributes to its corresponding layout element.
            name = itemView.findViewById(R.id.nameText);
            description = itemView.findViewById(R.id.descriptionText);
            price = itemView.findViewById(R.id.priceText);
            seller = itemView.findViewById(R.id.sellerText);

            //setting an onLongClickListener to the view items. If a user clicks and holds on an item it will select it
            itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    // on long click, the selected item will change its background to green and be added to the selected items array
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

            //setting an onClickListener to de-select and item and remove selected item from list. If a user clicks again on a selected item, it will de-select it.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if item is in selected mode, it will de select it, otherwise it will select it.
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
