package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // initialize variables
    private ListView myListView;
    private CustomAdapter myCustomAdapter;
    ArrayList<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize arraylist + add Course objects.
        courseList = new ArrayList<>();

        courseList.add(new Course("SWENG888","Everton Guimares", "Mobile App development: Learning how to create applications in android."));
        courseList.add(new Course("SWENG581","Joanna DeFranco", "Software Testing: Learning how to efficiently test software."));
        courseList.add(new Course("SWENG505","Phillip Laplante", "Software Project Management: Learning how to manage software projects."));
        courseList.add(new Course("SWENG586","Phillip Laplante", "Requirements Engineeering: Learning how to extract software requirements from business stakeholders."));

        //initialize adapter with arraylist
        myCustomAdapter = new CustomAdapter(MainActivity.this,courseList);

        myListView =findViewById(R.id.listView);
        myListView.setAdapter(myCustomAdapter);

        //Set listener to observe items clicked within listview, if item is clicked navigate to description activity class

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = (Course) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this,CourseDescriptionActivity.class);
                intent.putExtra("selectedCourse", selectedCourse);
                startActivity(intent);
            }
        });


    }

    //On resume,display a snackbar saying which course the user returned from

    @Override
    protected void onResume() {
        super.onResume();
        boolean returnFromCourseDetail = getIntent().getBooleanExtra("RETURN", false);
        String returnVal = getIntent().getStringExtra("Course");


        if (returnFromCourseDetail) {
            showSnackbar("Returned from course  "+ returnVal);
        }
    }

    //method to display snackbar
    private void showSnackbar(String message) {
        Snackbar.make(myListView, message, Snackbar.LENGTH_SHORT).show();
    }

}