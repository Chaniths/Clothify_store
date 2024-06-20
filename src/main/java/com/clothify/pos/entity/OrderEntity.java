package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Order")
@Table(name = "Order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String orderId;
    private String customerId;
    private String customerName;
    private String contact;
    private String productId;
    private String productName;
    private Integer qty;
    private  Double amount;
    private Double total;
    private Boolean status;
}
