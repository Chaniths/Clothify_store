package com.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Integer productId;
    private String productName;
    private Integer supplierId;
    private Double unitPrice;
    private String category;
    private Integer qty;
    private Image img;


}
