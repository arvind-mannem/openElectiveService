package com.openelective.project.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course_details")
@Data
@NoArgsConstructor
public class CourseDetails {

    @Id
    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "course_branch", nullable = false)
    private String courseBranch;

    @Column(name = "sec_count")
    private int secCount;

    @Column(name = "seat_count_per_section")
    private int seatCountPerSection;

    @Column(name = "remaining_seats", nullable = false)
    private int remainingSeats;

    public CourseDetails(String courseName, String courseBranch, int remainingSeats) {
        this.courseName = courseName;
        this.courseBranch = courseBranch;
        this.remainingSeats = remainingSeats;
    }

}
