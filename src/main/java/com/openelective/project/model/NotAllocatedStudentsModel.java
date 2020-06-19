package com.openelective.project.model;

import lombok.Data;

@Data
public class NotAllocatedStudentsModel {
    private String rollNumber;
    private String name;
    private String branch;
    private int year;
    private double cgpa;
    private String option1;
    private String option2;
    private String option3;

    public NotAllocatedStudentsModel(String rollNumber, String name, String branch, int year, double cgpa, String option1, String option2, String option3) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.branch = branch;
        this.year = year;
        this.cgpa = cgpa;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }
}
