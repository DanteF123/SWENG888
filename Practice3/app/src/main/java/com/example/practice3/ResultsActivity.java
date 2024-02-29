package com.example.practice3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
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
    boolean successfulEmail = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_layout);

        recyclerView=findViewById(R.id.recyclerView);

        emailButton = findViewById(R.id.emailButton);



        productList = getIntent().getParcelableArrayListExtra("data");


        selectedProductAdapter=new SelectedProductAdapter(productList);

        recyclerView.setAdapter(selectedProductAdapter);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
                selectedProductAdapter.removeAll(productList);
                successfulEmail=true;

            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

        if(successfulEmail){
            Toast.makeText(ResultsActivity.this,"Email sent successfully",Toast.LENGTH_SHORT).show();
        }
    }

    public void sendEmail() {
        String[] TO = {"dantemfusaro@gmail.com"}; // Email address where you want to send the email
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Products"); // Email subject
        emailIntent.putExtra(Intent.EXTRA_TEXT, parseProducts(productList)); // Email message body

        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Choose email client:"));
    }

    public String parseProducts(ArrayList<Product> products){
        String productDetails = "";
        for(Product p:products){
            productDetails+=p.getName()+": "+p.getDescription()+"\n";
        }

        return productDetails;

    }




}