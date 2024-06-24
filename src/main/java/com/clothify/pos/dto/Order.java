package com.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String customerId;
    private String customerName;
    private String contact;
   // private String productId;
   //private String productName;
    private Integer qty;
    private  Double amount;
    private Double total;
    private Boolean status;

}
