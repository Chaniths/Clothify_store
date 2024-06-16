package com.clothify.pos.dto.system;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    public Product(Image img) {
        this.img = img;
    }

    private Integer productId;
    private String productName;
    private Integer supplierId;
    private Integer qtyOnHand;
    private Image img;


}
