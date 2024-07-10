package com.clothify.pos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @JsonIgnore
    private Integer id;
    private String employeeId;
    private String employeeName;
    private int age;
    private String nic;
    private String contact;
    private String address;
    private Date recruited;

}
