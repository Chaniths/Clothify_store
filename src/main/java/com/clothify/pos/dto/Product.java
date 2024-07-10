package com.clothify.pos.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @JsonIgnore
    private Integer id;
    private String productId;
    private String productName;
    private String supplierId;
    private Double unitPrice;
    private String category;
    private Integer qty;

}
