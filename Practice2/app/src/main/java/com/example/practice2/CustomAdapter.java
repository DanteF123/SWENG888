package com.example.practice2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Course> {
    // creating constructor
    public CustomAdapter(@NonNull Context context, @NonNull List<Course> objects) {
        super(context, R.layout.item_list_layout, objects);
    }

    //inflating the view in the listview
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listViewElement = convertView;

        if (listViewElement == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            listViewElement = inflater.inflate(R.layout.item_list_layout, parent, false);
        }

        Course course = getItem(position);

        TextView courseID = listViewElement.findViewById(R.id.course_title);
        TextView instructorName = listViewElement.findViewById(R.id.instructor_name);

        courseID.setText("ID: "+course.getCourseID());
        instructorName.setText("Professor: "+course.getProfessorName());

        return listViewElement;

    }
}
