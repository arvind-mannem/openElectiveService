package com.openelective.project.service;

import com.openelective.project.model.Student;
import com.openelective.project.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return this.studentRepository.findAll();
    }

    public Optional<Student> getByStudentId(String id) {
        return this.studentRepository.findById(id.toUpperCase());
    }

    public Student createStudent(Student s) {
        if(studentRepository.existsById(s.getId().toUpperCase())){
            Optional<Student> oldInfo = studentRepository.findById(s.getId().toUpperCase());
            Student student= oldInfo.get();
            s.setOE1(student.getOE1());
            s.setOE2(student.getOE2());
            s.setOE3(student.getOE3());
            s.setAllocated(student.isAllocated());
            s.setAllocatedCourse(student.getAllocatedCourse());
        }
        s.setId(s.getId().toUpperCase());
        s.setBranch(s.getBranch().toUpperCase());
        return this.studentRepository.save(s);
    }

    public void updateStudentAllocatedStatus(Student student,boolean status){
        Optional<Student> std = this.studentRepository.findById(student.getId());
        Student newStudent=std.get();
        newStudent.setAllocated(status);
        this.studentRepository.save(newStudent);
    }

    public void updateStudentAllocatedCourse(Student student,String course){
        Optional<Student> std = this.studentRepository.findById(student.getId());
        Student newStudent = std.get();
        newStudent.setAllocatedCourse(course);
        this.studentRepository.save(newStudent);
    }

    public List<String> getStudentPursedCourses(String studentid){
        List<String> studiedCourses = new ArrayList<>();
        studentid=studentid.toUpperCase();
        if(studentRepository.existsById(studentid)) {

            String oE1 = getByStudentId(studentid).get().getOE1();
            String oE2 = getByStudentId(studentid).get().getOE2();
            String oE3 = getByStudentId(studentid).get().getOE3();
            if (oE1 != null)
                studiedCourses.add(oE1);
            if (oE2 != null)
                studiedCourses.add(oE2);
            if (oE3 != null)
                studiedCourses.add(oE3);
        }
        return studiedCourses;
    }

    public List<Student> getNonAllocatedFourthYearStudents(){
        return this.studentRepository.findAllByAllocatedAndYearOrderByCgpaDescBacklogsAsc(false,4);
    }

    public List<Student> getNonAllocatedThirdYearStudents(){
        return this.studentRepository.findAllByAllocatedAndYearOrderByCgpaDescBacklogsAsc(false,3);
    }

    public List<Student> getAllNonAllocatedStudents(){
        return this.studentRepository.findAllByAllocatedOrderByCgpaDescBacklogsAsc(false);
    }

    public List<Student> getAllAllocatedStudents(){
        return this.studentRepository.findAllByAllocated(true);
    }

    //method to update student with allocated course
    public void registerCourseForStudent(Student student, String course){

        Optional<Student> std = this.studentRepository.findById(student.getId());
        Student newStudent = std.get();
        String oe1=newStudent.getOE1();
        String oe2=newStudent.getOE2();
        String oe3=newStudent.getOE3();
        if(oe1 == null)
            newStudent.setOE1(course);
        else if(oe2 == null)
            newStudent.setOE2(course);
        else if(oe3 == null)
            newStudent.setOE3(course);

        this.studentRepository.save(newStudent);
    }

    public List<Student> getAllocatedThirdYearStudents(){
        return this.studentRepository.findAllByAllocatedAndYearOrderByCgpaDescBacklogsAsc(true,3);
    }

    public List<Student> getAlloatedFourthYearStudents(){
        return this.studentRepository.findAllByAllocatedAndYearOrderByCgpaDescBacklogsAsc(true,4);
    }

    public List<Student> getAllThirdYearStudents(){
        return this.studentRepository.findAllByYearOrderByCgpaDescBacklogsAsc(3);
    }

    public List<Student> getAllFourthYearStudents(){
        return this.studentRepository.findAllByYearOrderByCgpaDescBacklogsAsc(4);
    }

    public void deleteAllStudentRecords(){
        this.studentRepository.deleteAll();
    }

}
