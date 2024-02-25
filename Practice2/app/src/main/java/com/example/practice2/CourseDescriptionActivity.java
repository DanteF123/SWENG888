package com.example.practice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CourseDescriptionActivity extends AppCompatActivity {
    //initializing variables
    TextView courseDescription;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Assigning widgets
        setContentView(R.layout.course_detail_layout);

        btn = findViewById(R.id.backButton);

        courseDescription = findViewById(R.id.courseDescription);

        //Grabbing the extra from the preceding activity
        Intent intent= getIntent();
        Course selectedCourse = (Course) intent.getSerializableExtra("selectedCourse");

        //Setting description to match course that was selected by user on previous Activity
        courseDescription.setText(selectedCourse.getDescription());

        //Creating functionality for clicking the back button
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Establishing intent to return to main activity. The intent will include a boolean Return clause,
                // and will also contain the course the user was on.
                Intent intent = new Intent(CourseDescriptionActivity.this, MainActivity.class);
                intent.putExtra("RETURN", true);
                intent.putExtra("Course",selectedCourse.getCourseID().toString());
                startActivity(intent);
            }
        });

    }
}