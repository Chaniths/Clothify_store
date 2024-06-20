package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Supplier")
@Table(name = "Supplier")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String supplierId;
    private String supplierName;
    private Integer productId;
    private String productName;
    private String contact;
    private String email;
    private String company;
}
