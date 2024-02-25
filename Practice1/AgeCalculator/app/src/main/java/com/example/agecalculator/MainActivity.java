package com.example.agecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

//    instantiate variables
    Button mBtn;
    EditText mDateOfBirth, mFirstName, mLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // setting variables to their corresponding IDs
        mDateOfBirth = findViewById(R.id.DOBeditText);
        mFirstName = findViewById(R.id.firstNameEditText);
        mLastName = findViewById(R.id.lastNameEditText);
        mBtn = findViewById(R.id.button);


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userDOBString = mDateOfBirth.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Date user_date;

                // try catch block to attempt to process user inputs
                try {
                    // Parsing user's string input to pull the date.
                    user_date = formatter.parse(userDOBString);

                    //Call method to determine age
                    double calculatedAge = calculateAge(user_date);

                    //Grab first and last names from user input
                    String userFirstName = mFirstName.getText().toString();
                    String userLastName = mLastName.getText().toString();
                    //if we have both first and last name, display user name and age in toast
                    if(!userFirstName.isEmpty()&!userLastName.isEmpty()){
                        Toast.makeText(getApplicationContext(),userFirstName+" "+userLastName+" is "+calculatedAge,Toast.LENGTH_LONG).show();
                    }
                    //If there is a failure in parsing the date, offer a correct format and prompt user
                } catch (ParseException e) {
                    Toast.makeText(getApplicationContext(),"Invalid Date Format. Please try dd-MM-yyyy (31-12-1995)",Toast.LENGTH_LONG).show();
                }

                //if we are missing a first name, prompt user to provides
                if(mFirstName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please provide a first name",Toast.LENGTH_LONG).show();
                }

                //if we are missing a last name, prompt user to provide
                if(mLastName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please provide a last name",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

//    method that takes birthday as input, and calculates age
    public double calculateAge(Date birthday){
        long currentTime = System.currentTimeMillis();
        long birthTime = birthday.getTime();
        long millisecondsPerYear = (long) (365.25 * 24 * 60 * 60 * 1000);
        double age = (double) (currentTime - birthTime) / millisecondsPerYear;


        return Math.round(age * 10.0) / 10.0;

    }

}