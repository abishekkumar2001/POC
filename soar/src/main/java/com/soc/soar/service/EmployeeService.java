package com.soc.soar.service;

import com.soc.soar.common.PageResponse;
import com.soc.soar.dto.EmployeeDTO;
import com.soc.soar.entity.Employee;
import com.soc.soar.entity.Gender;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> getAllEmployees();

    public Employee addEmployee(EmployeeDTO employeeDTO);

    public Optional<Employee> getEmployeeById(String id);

    public Employee updateEmployee(Employee employee);

    public String deleteEmployee(String id);

    public List<Employee> getAllEmployeesByDesignation(String designation);

    public List<Employee> searchEmployee(String name, Gender gender);

    public List<Employee> searchEmployeeByNameOrGender(String name, Gender gender);

    public PageResponse<Employee> searchEmployeeWithPagination(String name, Gender gender, Pageable pageable);

}
