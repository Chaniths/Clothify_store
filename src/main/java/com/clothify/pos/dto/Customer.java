package com.clothify.pos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @JsonIgnore
    private Integer id;
    private String customerId;
    private String customerName;
    private String contact;
    private String email;
    private Date dob;
    private String address;
}
