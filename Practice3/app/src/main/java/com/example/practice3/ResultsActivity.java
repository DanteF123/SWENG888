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
// Results activity created to manage data following the user's selection.
public class ResultsActivity extends AppCompatActivity {
    //Instantiating and assigning variables
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

        //attaching variables to their corresponding layout item.

        recyclerView=findViewById(R.id.recyclerView);

        emailButton = findViewById(R.id.emailButton);

        // grabs the ArrayList of selected items sent by the main activity.
        productList = getIntent().getParcelableArrayListExtra("data");

        // attach the adapter to the selected items.
        selectedProductAdapter=new SelectedProductAdapter(productList);

        recyclerView.setAdapter(selectedProductAdapter);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //Setting a click listener to the email button, to send the list of items to an email upon click.
        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if the user has selected at least one item, call the send email method, then remove all items from the screen.
                if(selectedProductAdapter.getItemCount()>0){
                    sendEmail();
                    selectedProductAdapter.removeAll(productList);
                    successfulEmail=true;

                }
                // Otherwise tell the user to select at least one item.
                else{
                    Toast.makeText(ResultsActivity.this,"Please select at least one item",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

    //onResume used for when the user navigates back from sending an email, it will notify the user that the email was sent successfully.
    @Override
    protected void onResume() {
        super.onResume();

        if(successfulEmail){
            Toast.makeText(ResultsActivity.this,"Email sent successfully",Toast.LENGTH_SHORT).show();
        }
    }

    //method used to navigate to the email, and populate the email with the data from the selected items.
    public void sendEmail() {
        String[] TO = {"sweng888mobileapps@gmail.com"}; // Email address to send the email
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Selected Products"); // Email subject
        emailIntent.putExtra(Intent.EXTRA_TEXT, parseProducts(productList)); // Email message body

        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Choose email client:"));
    }

    // method used to parse the class of each selected item, and concatenating it to a string to send in a cohesive email.
    public String parseProducts(ArrayList<Product> products){
        String productDetails = "";
        for(Product p:products){
            productDetails+=p.getName()+": "+p.getDescription()+"\n";
        }

        return productDetails;

    }




}