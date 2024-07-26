package com.soc.soar.service;

import com.soc.soar.common.PageResponse;
import com.soc.soar.dto.EmployeeDTO;
import com.soc.soar.entity.Employee;
import com.soc.soar.entity.Gender;
import com.soc.soar.repository.EmployeeRepo;
import com.soc.soar.exception.EmployeeNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee addEmployee(EmployeeDTO employeeDTO) {
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        return employeeRepo.save(employee);
    }


    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepo.findById(id);
    }

    public Employee updateEmployee(Employee employee) {

        if(!employeeRepo.existsById(employee.getId())){
            throw new EmployeeNotFoundException("Employee not found");
        }
        return employeeRepo.save(employee);
    }

    public String deleteEmployee(String id) {
        if(!employeeRepo.existsById(id)){
            throw new EmployeeNotFoundException("No employee found to delete");
        }
        employeeRepo.deleteById(id);
        return "Deleted Successfully";
    }

    public List<Employee> getAllEmployeesByDesignation(String designation) {
        return employeeRepo.findAllByDesignation(designation);
    }

    public List<Employee> searchEmployee(String name, Gender gender) {
        return employeeRepo.findByNameStartingWithAndGenderIgnoreCase(name,gender.name());
    }

    public List<Employee> searchEmployeeByNameOrGender(String name, Gender gender) {
        String genderValue = gender != null ? gender.name() : null;
        if(gender != null){
            return employeeRepo.findByNameStartingWithAndGenderIgnoreCase(name,gender.name());
        }
        return employeeRepo.findByNameStartingWithOrGenderIgnoreCase(name,genderValue);
    }

    PageResponse pageResponse = new PageResponse();

    public PageResponse<Employee> searchEmployeeWithPagination(String name, Gender gender, Pageable pageable) {

        Page<Employee> employee;
        String genderValue = gender != null ? gender.name() : null;

        if(gender != null){
            employee = employeeRepo.findByNameStartingWithAndGenderIgnoreCaseAndAll(name,genderValue,pageable);
        } else {
            employee = employeeRepo.findByNameStartingWithOrGenderIgnoreCaseAndAll(name,genderValue,pageable);
        }

        pageResponse.setData(employee.getContent());
        pageResponse.setPageNumber(employee.getNumber());
        pageResponse.setPageSize(employee.getSize());
        pageResponse.setTotalPages(employee.getTotalPages());
        pageResponse.setTotalElements(employee.getTotalElements());
        return pageResponse;
    }
}

