package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResultsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private SelectedProductAdapter selectedProductAdapter;
    private Button emailButton;
    private ArrayList<Product> productList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        recyclerView=findViewById(R.id.recyclerView);

        emailButton = findViewById(R.id.emailButton);


        Bundle bundle = getIntent().getExtras();
        productList = bundle.getParcelableArrayList("data");


//
//
//        selectedProductAdapter=new SelectedProductAdapter(productList);
//
//        recyclerView.setAdapter(selectedProductAdapter);
//
//        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);




    }



}