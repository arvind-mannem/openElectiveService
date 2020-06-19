package com.openelective.project.model;

import lombok.Data;

@Data
public class InEligibleStudentsModel {
    private String rollNumber;
    private String name;
    private String branch;
    private int year;
    private double cgpa;
    private int backlogs;

    public InEligibleStudentsModel(String rollNumber, String name, String branch, int year, double cgpa, int backlogs) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.branch = branch;
        this.year = year;
        this.cgpa = cgpa;
        this.backlogs = backlogs;
    }
}
