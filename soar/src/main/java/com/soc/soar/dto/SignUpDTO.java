package com.soc.soar.dto;

import com.soc.soar.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDTO {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Gender gender;

}
