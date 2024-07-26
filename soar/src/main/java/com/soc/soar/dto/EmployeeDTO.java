package com.soc.soar.dto;

import com.soc.soar.entity.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 4, max = 50, message = "Name should not be more than 50 characters or less than 4 characters")
    private String name;

    @NotEmpty(message = "Designation is required")
    private String designation;

    @NotNull(message = "Salary is required")
    @Min(10000)
    @Max(100000)
    private Integer salary;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @Email(message = "Not a valid Email")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
}