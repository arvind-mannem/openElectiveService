package com.openelective.project.repository;

import com.openelective.project.model.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseDetailsRepository extends JpaRepository<CourseDetails, String> {
    public static final String FIND_COURSES = "SELECT course_name FROM course_details";
    public static final String FIND_COURSES_BY_BRANCH="SELECT c.course_name from course_details c where c.course_branch =:branch";
    public static final String FIND_BRANCHES = "SELECT course_branch FROM course_details";
    public static final String FIND_BRANCH_BY_COURSE = "SELECT c.course_branch from course_details c where c.course_name =:course";

    @Query(value = FIND_COURSES, nativeQuery = true)
    public List<String> findCourses();

    @Query(value = FIND_BRANCHES, nativeQuery = true)
    public List<String> findBranches();

    @Query(value = FIND_COURSES_BY_BRANCH, nativeQuery = true)
    public List<String> findCoursesByBranch(@Param("branch") String branch);

    @Query(value = FIND_BRANCH_BY_COURSE, nativeQuery = true)
    public String findBranchByCourse(@Param("course") String course);

    public CourseDetails findByCourseName(String course);
}
