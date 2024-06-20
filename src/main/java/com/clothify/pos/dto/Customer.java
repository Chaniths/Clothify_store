package com.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String customerId;
    private String customerName;
    private String contact;
    private String email;
    private Date dob;
    private String address;
}
