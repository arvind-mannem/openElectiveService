package com.openelective.project.model;

import lombok.Data;

@Data
public class AllocatedStudentsModel {

    private String rollNumber;
    private String name;
    private String branch;
    private int year;
    private double cgpa;
    private String allocatedCourse;

    public AllocatedStudentsModel(String rollNumber,String name,String branch, int year,double cgpa, String allocatedCourse) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.branch = branch;
        this.year = year;
        this.cgpa = cgpa;
        this.allocatedCourse = allocatedCourse;
    }
}
