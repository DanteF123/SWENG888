package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private ProductDatabaseHelper databaseHelper;
    private ProductAdapter productAdapter;
    private Button button2;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        button2 = findViewById(R.id.button2);

        databaseHelper = new ProductDatabaseHelper(this);

        List<Product> products;
        ArrayList<Product> selectedProducts;



        if(databaseHelper.isDatabaseEmpty()){
            databaseHelper.populateProductDatabase();
        }

        products=databaseHelper.getAllProducts();

        productAdapter=new ProductAdapter(products);

        recyclerView.setAdapter(productAdapter);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);



        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);

                intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) productAdapter.selectedItems);
                startActivity(intent);
                }

                }


        );

    }

}