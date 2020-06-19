package com.openelective.project.repository;

import com.openelective.project.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    List<Student> findAllByAllocatedAndYearOrderByCgpaDescBacklogsAsc(boolean allocated,int year);
    List<Student> findAllByAllocatedOrderByCgpaDescBacklogsAsc(boolean allocated);
    List<Student> findAllByAllocated(boolean allocated);
    List<Student> findAllByYearOrderByCgpaDescBacklogsAsc(int year);

}
