package com.openelective.project.service;

import com.openelective.project.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {

    private StudentService studentService;
    private CourseService courseService;
    private AllocationService allocationService;

    public ReportsService(StudentService studentService, CourseService courseService, AllocationService allocationService){
        this.studentService = studentService;
        this.courseService = courseService;
        this.allocationService = allocationService;
    }

    public List<AllocatedStudentsModel> generateAllocatedStudentsCsv(){
        List<Student> allocatedStudents = studentService.getAllAllocatedStudents();

        List<AllocatedStudentsModel> modifiedAllocatedStudentList = new ArrayList<>();
        for(int i=0;i<allocatedStudents.size();i++){
            String id = allocatedStudents.get(i).getId();
            String name = allocatedStudents.get(i).getName();
            int year = allocatedStudents.get(i).getYear();
            double cgpa = allocatedStudents.get(i).getCgpa();
            String course = allocatedStudents.get(i).getAllocatedCourse();
            String branch = allocatedStudents.get(i).getBranch();

            modifiedAllocatedStudentList.add(new AllocatedStudentsModel(id,name,branch,year,cgpa,course));
        }
        return modifiedAllocatedStudentList;
    }

    public List<NotAllocatedStudentsModel> generateNotAllocatedStudentsCsv(){
        List<Student> notAllocatedStudents = studentService.getAllNonAllocatedStudents();
        //filter this list from students who are not eligible
        notAllocatedStudents.removeAll(allocationService.getInEligibleStudentsList());

        List<NotAllocatedStudentsModel> modifiedNotAllocatedStudentsList = new ArrayList<>();
        for(int i=0;i<notAllocatedStudents.size();i++) {
            String id = notAllocatedStudents.get(i).getId();
            String name = notAllocatedStudents.get(i).getName();
            String branch = notAllocatedStudents.get(i).getBranch();
            int year = notAllocatedStudents.get(i).getYear();
            double cgpa = notAllocatedStudents.get(i).getCgpa();
            String op1 = notAllocatedStudents.get(i).getOP1();
            String op2 = notAllocatedStudents.get(i).getOP2();
            String op3 = notAllocatedStudents.get(i).getOP3();

            modifiedNotAllocatedStudentsList.add(new NotAllocatedStudentsModel(id, name, branch, year, cgpa, op1, op2, op3));
        }
        return modifiedNotAllocatedStudentsList;
    }

    public List<InEligibleStudentsModel> generateInEligibleStudents(){
        List<Student> inEligibleStudents = allocationService.getInEligibleStudentsList();

        List<InEligibleStudentsModel> modifiedInEligibleStudents = new ArrayList<>();
        for(int i=0;i<inEligibleStudents.size();i++){
            String id = inEligibleStudents.get(i).getId();
            String branch = inEligibleStudents.get(i).getBranch();
            String name = inEligibleStudents.get(i).getName();
            int year = inEligibleStudents.get(i).getYear();
            double cgpa = inEligibleStudents.get(i).getCgpa();
            int backlogs = inEligibleStudents.get(i).getBacklogs();

            modifiedInEligibleStudents.add(new InEligibleStudentsModel(id, name, branch, year, cgpa, backlogs));
        }
        return modifiedInEligibleStudents;
    }

    public List<RemainingSeatsOfCourseModel> getRemainingSeatsCountForAllCourses(){
        List<String> courses = courseService.getAllCourseNames();
        List<RemainingSeatsOfCourseModel> remainingSeats = new ArrayList<>();
        for(int i=0;i<courses.size();i++){
            String course = courses.get(i);
            String branch = courseService.getBranchOfCourse(course);
            int remainingSeatsOfCourse = courseService.getRemainingSeatsOfCourse(course);

            remainingSeats.add(new RemainingSeatsOfCourseModel(course, branch, remainingSeatsOfCourse));
        }

        return remainingSeats;
    }

}
