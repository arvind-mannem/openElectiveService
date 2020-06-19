package com.openelective.project.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@NoArgsConstructor
public class Student {
    @Id
    @Column(name = "student_id")
    @NotNull
    private String id;
    @Column(name = "student_name")
    @NotNull
    private String name;
    @Column(name = "branch")
    @NotNull
    private String branch;
    @Column(name = "year")
    @NotNull
    private int year;
    @Column(name = "CGPA")
    @NotNull
    private double cgpa;
    @Column(name = "backlogs")
    @NotNull
    private int backlogs;
    @Column(name = "option_1")
    @NotNull
    private String OP1;
    @Column(name = "option_2")
    @NotNull
    private String OP2;
    @Column(name = "option_3")
    @NotNull
    private String OP3;

    //Registration form does not include these fields
    @Column(name = "open_elective_1")
    private String OE1;
    @Column(name = "open_elective_2")
    private String OE2;
    @Column(name = "open_elective_3")
    private String OE3;
    @Column(name = "isAllocated")
    private boolean allocated;
    @Column(name= "allocated_course")
    private String allocatedCourse;

    public Student(String id, String name, int year, double cgpa, int backlogs, String OP1, String OP2, String OP3) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.cgpa = cgpa;
        this.backlogs = backlogs;
        this.OP1 = OP1;
        this.OP2 = OP2;
        this.OP3 = OP3;
    }

}