package com.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String employeeId;
    private String employeeName;
    private int age;
    private String nationalId;
    private String contact;
    private String address;
    private Date employmentStart;

}
