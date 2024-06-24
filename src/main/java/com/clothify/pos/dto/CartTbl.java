package com.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartTbl {

    private String productId;
    private String productName;
    private Integer qty;
    private Double unitPrice;
    private Double amount;

}
