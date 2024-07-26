package com.soc.soar.repository;

import com.soc.soar.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {

    List<Employee> findAllByDesignation(String designation);

    @Query(value = "SELECT * FROM employee WHERE name LIKE :name% AND gender = :gender", nativeQuery = true)
    List<Employee> findByNameStartingWithAndGenderIgnoreCase(String name, String gender);

    @Query(value = "SELECT * FROM employee WHERE name LIKE :name% OR gender = :gender ", nativeQuery = true)
    List<Employee> findByNameStartingWithOrGenderIgnoreCase(String name, String gender);

    @Query(value = "SELECT * from employee WHERE name LIKE :name% AND gender = :gender",nativeQuery = true)
    Page<Employee> findByNameStartingWithAndGenderIgnoreCaseAndAll(String name, String gender, Pageable pageable);

    @Query(value = "SELECT * from employee WHERE name LIKE :name% OR gender = :gender",nativeQuery = true)
    Page<Employee> findByNameStartingWithOrGenderIgnoreCaseAndAll(String name, String gender, Pageable pageable);

}
