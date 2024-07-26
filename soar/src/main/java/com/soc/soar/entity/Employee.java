package com.soc.soar.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @UuidGenerator
    private String id;

    private String name;
    private String designation;
    private Integer salary;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;
    private String phoneNumber;

}
