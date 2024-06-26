package com.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String supplierId;
    private String supplierName;
    private String productId;
    private String productName;
    private String contact;
    private String email;
    private String company;

}
