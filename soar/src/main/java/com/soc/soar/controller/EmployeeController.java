package com.soc.soar.controller;

import com.soc.soar.common.PageResponse;
import com.soc.soar.dto.EmployeeDTO;
import com.soc.soar.entity.Employee;
import com.soc.soar.entity.Gender;
import com.soc.soar.service.EmployeeServiceImpl;
import com.soc.soar.utils.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping
    public List<Employee> getAllEmployees(@RequestHeader(name = "authorization") String authorizationHeader) throws Exception{

        String authToken = authorizationHeader.substring(7); // Remove "Bearer " prefix

        // Validate the JWT token
        if (!jwtUtils.validateJwtToken(authToken)) {
            throw new Exception("Unauthorized access");
        }

        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee addEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        return employeeService.addEmployee(employeeDTO);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable String id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("/designation/{designation}")
    public List<Employee> getAllEmployeesByDesignation(@PathVariable String designation){
        return employeeService.getAllEmployeesByDesignation(designation);
    }

    //Search API Implementation
    @GetMapping("/search")
    public List<Employee> searchEmployee(@RequestParam String name, @RequestParam Gender gender){
        return employeeService.searchEmployee(name,gender);
    }

    @GetMapping("/optional/search")
    public List<Employee> searchEmployeeByNameOrGender(@RequestParam String name, @RequestParam(required = false) Gender gender){
        return employeeService.searchEmployeeByNameOrGender(name,gender);
    }

    @GetMapping("/search/records")
    public PageResponse<Employee> searchEmployeeWithPagination(@RequestParam String name,
                                                               @RequestParam(required = false) Gender gender,
                                                               @RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page,size);
        return employeeService.searchEmployeeWithPagination(name,gender,pageable);
    }

}
