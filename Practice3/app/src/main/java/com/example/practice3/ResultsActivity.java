package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ProductDatabaseHelper databaseHelper;
    private ProductAdapter productAdapter;
    private Button buttonBackSelection;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        recyclerView=findViewById(R.id.recyclerView);

        databaseHelper = new ProductDatabaseHelper(this);

        List<Product> products;


        if(databaseHelper.isDatabaseEmpty()){
            databaseHelper.populateProductDatabase();
        }

        products=databaseHelper.getAllProducts();

        productAdapter=new ProductAdapter(products);

        recyclerView.setAdapter(productAdapter);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

    }

}