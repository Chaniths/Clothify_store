package com.clothify.pos.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {

    @JsonIgnore
    private Integer id;
    private String productId;
    private String customerId;
    private Integer qty;
    private Double amount;


}
