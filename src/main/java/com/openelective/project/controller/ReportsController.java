package com.openelective.project.controller;

import com.openelective.project.model.AllocatedStudentsModel;
import com.openelective.project.model.InEligibleStudentsModel;
import com.openelective.project.model.NotAllocatedStudentsModel;
import com.openelective.project.model.RemainingSeatsOfCourseModel;
import com.openelective.project.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/reports")
public class ReportsController {

    @Autowired
    private ReportsService reportsService;

    @GetMapping("/allocated-students")
    public void exportAllocatedStudentsCSV(HttpServletResponse response) throws Exception {

        //set file name and content type
        String filename = "allocatedStudents.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"RollNumber","Name","Branch","Year","Cgpa","AllocatedCourse"};
        csvWriter.writeHeader(csvHeader);
        List<AllocatedStudentsModel> allocatedStudents = reportsService.generateAllocatedStudentsCsv();

        for(AllocatedStudentsModel allocatedStudent : allocatedStudents){
            csvWriter.write(allocatedStudent,csvHeader);
        }

        csvWriter.close();
    }


    @GetMapping("/not-allocated-students")
    public void exportNotAllocatedStudentsCsv(HttpServletResponse response) throws Exception{

        //set file name and content type
        String filename = "notAllocatedStudents.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"RollNumber","Name","Branch","Year","Cgpa","Option1","Option2","Option3"};
        csvWriter.writeHeader(csvHeader);
        List<NotAllocatedStudentsModel> notAllocatedStudents = reportsService.generateNotAllocatedStudentsCsv();
        for(NotAllocatedStudentsModel notAllocatedStudent : notAllocatedStudents){
            csvWriter.write(notAllocatedStudent, csvHeader);
        }
        csvWriter.close();
    }

    @GetMapping("/ineligible-students")
    public void exportInEligibleStudentsCsv(HttpServletResponse response) throws Exception{

        //set file name and content type
        String filename = "inEligibleStudents.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"RollNumber","Name","Branch","Year","Cgpa","Backlogs"};
        csvWriter.writeHeader(csvHeader);
        List<InEligibleStudentsModel> inEligibleStudents = reportsService.generateInEligibleStudents();
        for(InEligibleStudentsModel inEligibleStudent : inEligibleStudents){
            csvWriter.write(inEligibleStudent, csvHeader);
        }
        csvWriter.close();
    }

    @GetMapping("/remaining-seats")
    public void exportRemainingSeatsCsv(HttpServletResponse response) throws Exception{

        //set file name and content type
        String filename = "remainingSeats.csv";

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Course","Branch","RemainingSeatsOfCourse"};
        csvWriter.writeHeader(csvHeader);
        List<RemainingSeatsOfCourseModel> remainingSeatsOfCourses = reportsService.getRemainingSeatsCountForAllCourses();
        for(RemainingSeatsOfCourseModel remainingSeatsOfCourse : remainingSeatsOfCourses){
            csvWriter.write(remainingSeatsOfCourse, csvHeader);
        }
        csvWriter.close();
    }
}
