package com.clothify.pos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {

    @JsonIgnore
    private Integer id;
    private String supplierId;
    private String supplierName;
    private String productName;
    private String contact;
    private String email;
    private String company;
}
