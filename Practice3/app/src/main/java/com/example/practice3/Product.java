package com.example.practice3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Product implements Parcelable, Serializable {
    private String Name;
    private String Seller;
    private String Description;
    private String Price;
    protected Product(Parcel in) {
        Name = in.readString();
        Seller = in.readString();
        Description = in.readString();
        Price = in.readString();
    }

    public Product(String name, String seller, String description, String price) {
        Name = name;
        Seller = seller;
        Description = description;
        Price = price;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Seller);
        dest.writeString(Description);
        dest.writeString(Price);

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSeller() {
        return Seller;
    }

    public void setSeller(String seller) {
        Seller = seller;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
