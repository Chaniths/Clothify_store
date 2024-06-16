package com.clothify.pos.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String supplierName;
    private Integer productId;
    private String productName;
    private String contact;
    private String email;
    private String company;

}
