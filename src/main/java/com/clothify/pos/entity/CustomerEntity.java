package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Customer")
@Table(name = "Customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String customerId;
    private String customerName;
    private String contact;
    private String email;
    private Date dob;
    private String address;
}
