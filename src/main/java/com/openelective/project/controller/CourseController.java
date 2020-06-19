package com.openelective.project.controller;

import com.openelective.project.model.CourseDetails;
import com.openelective.project.service.CourseService;;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(value="/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDetails> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping(value="/publish-template")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDetails createCourse(@RequestBody CourseDetails course) {
        return courseService.createCourse(course);
    }

    @GetMapping(value="/{branch}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCoursesOfBranch(@PathVariable String branch){
        return courseService.getCoursesOfBranch(branch);
    }

    @GetMapping(value="/allCourseNames")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCourseNames(){
        return courseService.getAllCourseNames();
    }

    @GetMapping(value="/allBranches")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllBranches(){
        return courseService.getAllBranches();
    }

}
