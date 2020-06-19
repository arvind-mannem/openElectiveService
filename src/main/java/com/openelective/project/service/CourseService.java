package com.openelective.project.service;

import com.openelective.project.model.CourseDetails;
import com.openelective.project.repository.CourseDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    public CourseService(CourseDetailsRepository courseDetailsRepository, StudentService studentService) {
        this.courseDetailsRepository = courseDetailsRepository;
        this.studentService = studentService;
    }

    private final CourseDetailsRepository courseDetailsRepository;
    private final StudentService studentService;

    public List<CourseDetails> getAllCourses() {
        return courseDetailsRepository.findAll();
    }

    public CourseDetails createCourse(CourseDetails cd) {
        cd.setCourseBranch(cd.getCourseBranch().toUpperCase());
        cd.setCourseName(cd.getCourseName().toUpperCase());
        return courseDetailsRepository.save(cd);
    }

    public List<String> getAllCourseNames(){
        return courseDetailsRepository.findCourses();
    }

    public List<String> getAllBranches(){
        return courseDetailsRepository.findBranches();
    }

    public List<String> getCoursesOfBranch(String branch){
        return courseDetailsRepository.findCoursesByBranch(branch.toUpperCase());
    }

    public int getRemainingSeatsOfCourse(String course){
        return courseDetailsRepository.findByCourseName(course.toUpperCase()).getRemainingSeats();
    }

    public void updateRemainingSeatsOfCourse(String course){
        CourseDetails courseDetails = courseDetailsRepository.findByCourseName(course.toUpperCase());
        courseDetails.setRemainingSeats(courseDetails.getRemainingSeats()-1);
        this.courseDetailsRepository.save(courseDetails);
    }

    public void deleteAllCourseDetails(){
        courseDetailsRepository.deleteAll();
    }

    public String getBranchOfCourse(String course){
        return courseDetailsRepository.findBranchByCourse(course.toUpperCase());
    }
}
