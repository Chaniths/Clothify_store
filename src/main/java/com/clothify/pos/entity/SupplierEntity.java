package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
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
    private String productName;
    private String contact;
    private String email;
    private String company;
}
