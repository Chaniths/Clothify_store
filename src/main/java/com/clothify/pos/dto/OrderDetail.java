package com.clothify.pos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    private String productId;
    private String customerId;
    private Integer qty;
    private Double amount;


}
