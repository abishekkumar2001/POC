package com.soc.soar.repository;

import com.soc.soar.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

    Student findByName(String name);

    @Query(value = "SELECT * from student where name LIKE :name%",nativeQuery = true)
    List<Student> findByNameStartingWithIgnoreCase(String name);

    @Query(value = "SELECT * from student",nativeQuery = true)
    Page<Student> getStudents(Pageable pageable);
}
