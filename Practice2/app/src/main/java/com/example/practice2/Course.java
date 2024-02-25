package com.example.practice2;

import java.io.Serializable;

//Method that will contain all key data
public class Course implements Serializable {

    private String courseID;
    private String professorName;

    private String description;
    public Course(String courseID, String professorName, String description) {
        this.courseID = courseID;
        this.professorName = professorName;
        this.description = description;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
