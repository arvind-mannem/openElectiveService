package com.openelective.project.controller;

import com.openelective.project.model.Student;
import com.openelective.project.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/{id}")
    public Optional<Student> getAllStudents(@PathVariable String id) {
        return studentService.getByStudentId(id);
    }

    @PostMapping(value = "/save")
    public Student create(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping(value="/getNonAllocatedFourthYearStudents")
    public List<Student> getNonAllocatedFourthYearStudents(){
        return studentService.getNonAllocatedFourthYearStudents();
    }

    @GetMapping(value="/getStudentStudiedCourses/{studentid}")
    public List<String> getStudentStudiedCourses(@PathVariable String studentid){
        return studentService.getStudentPursedCourses(studentid);
    }

    @GetMapping(value="/deleteAllStudentRecords")
    public void deleteAllStudents(){
        studentService.deleteAllStudentRecords();
    }
}
