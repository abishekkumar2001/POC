package com.soc.soar.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDataDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 20)
    private String name;

    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "User name is required")
    private String userName;

//    @NotBlank(message = "Empty space is not allowed")
//    @Size(min = 5, max = 10, message = "Enter a password between 5 to 10 characters")
    private String password;
    
    @NotBlank(message = "Role is required")
    private String roles;
}
