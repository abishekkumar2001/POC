package com.soc.soar.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @NotEmpty(message = "Name is required")
    @Size(min = 5, max = 20)
    private String name;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Min(value = 1, message = "Minimum mark should be greater than or equal to one")
    @Max(value = 500, message = "Maximum marks is 500")
    @NotNull(message = "Marks is required")
    private Integer marks;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

}

