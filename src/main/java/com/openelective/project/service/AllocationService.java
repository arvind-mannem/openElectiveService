package com.openelective.project.service;

import com.openelective.project.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AllocationService {

    private StudentService studentService;
    private CourseService courseService;

    public AllocationService(StudentService studentService,CourseService courseService){
        this.studentService=studentService;
        this.courseService=courseService;
    }

    public boolean isEligible(double cgpa,int backlogs){
        if(cgpa>=7){
            if(backlogs<=1){
                return true;
            }
            else
                return false;
        }
        else if(cgpa>=5){
            if (backlogs<=0){
                return true;
            }
            else
                return false;
        }
        return false;
    }

    public List<Student> filterStudents(List<Student> students, String filter){
        List<Student> eligibleStudents = new ArrayList<Student>();
        List<Student> notEligibleStudents = new ArrayList<Student>();
        for(int i=0;i<students.size();i++){
            if(isEligible(students.get(i).getCgpa(),students.get(i).getBacklogs())){
                eligibleStudents.add(students.get(i));}
            else{
                notEligibleStudents.add(students.get(i));
            }
        }
        if (filter.equals("eligible"))
            return eligibleStudents;
        else{
            return notEligibleStudents;
        }
    }

    public String getStudentPreferredCourseBasedOnOption(Student student,int option){
        if(option == 1)
            return student.getOP1();
        else if(option == 2)
            return student.getOP2();
        else if(option ==3)
            return student.getOP3();
        return null;
    }
    public List<Student> allocateOEBasedOnOption(List<Student> students,int option){
        List<Student> notAllocated = new ArrayList<>();
        for(int i=0;i<students.size();i++){
            Student student = students.get(i);
            String course = getStudentPreferredCourseBasedOnOption(student,option);
            if(courseService.getRemainingSeatsOfCourse(course)>0){
                studentService.updateStudentAllocatedCourse(student,course);
                studentService.updateStudentAllocatedStatus(student,true);
                courseService.updateRemainingSeatsOfCourse(course);
            }
            else
                notAllocated.add(student);
        }
        return notAllocated;
    }

    public void allocateOE(List<Student> students){

        List<Student> notAllocatedFirstOption = allocateOEBasedOnOption(students,1);
        List<Student> notAllocatedSecondOption = allocateOEBasedOnOption(notAllocatedFirstOption, 2);
        List<Student> notAllocatedThirdOption = allocateOEBasedOnOption(notAllocatedSecondOption,3);

    }
    public void differentialAllocation(){
        List<Student> fourthYear = studentService.getNonAllocatedFourthYearStudents();
        List<Student> thirdYear = filterStudents(studentService.getNonAllocatedThirdYearStudents(),"eligible");
        List<Student> students = new ArrayList<Student>(fourthYear);
        students.addAll(thirdYear);
        allocateOE(students);
    }
    public void combinedAllocation(){
        List<Student> students = filterStudents(studentService.getAllNonAllocatedStudents(),"eligible");
        allocateOE(students);
    }

    public List<Student> getInEligibleStudentsList(){
        return filterStudents(studentService.getAllNonAllocatedStudents(), "inEligible");
    }
    public void completeAllocationForSemester(){
        List<Student> students = studentService.getAllStudents();

        //delete all course details
        courseService.deleteAllCourseDetails();

        //update all students with their allocated course as their respective open elective
        for(int i=0;i<students.size();i++){
            studentService.registerCourseForStudent(students.get(i),students.get(i).getAllocatedCourse());
        }

        //update all student allocated status to false
        for(int i=0;i<students.size();i++){
            studentService.updateStudentAllocatedStatus(students.get(i),false);
        }

        //update all students allocatedCourse to null
        for(int i=0;i<students.size();i++){
            studentService.updateStudentAllocatedCourse(students.get(i),null);
        }
    }
}
