package com.openelective.project.model;

import lombok.Data;

@Data
public class RemainingSeatsOfCourseModel {

    private String course;
    private String branch;
    private int remainingSeatsOfCourse;

    public RemainingSeatsOfCourseModel(String course, String branch, int remainingSeatsOfCourse) {
        this.course = course;
        this.branch = branch;
        this.remainingSeatsOfCourse = remainingSeatsOfCourse;
    }
}
