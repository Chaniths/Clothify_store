package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.lang.reflect.Type;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Product")
@Table(name = "Product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer productId;
    private String productName;
    private Integer supplierId;
    private Double unitPrice;
    private String category;
    private Integer qty;
    private Image img;
}
