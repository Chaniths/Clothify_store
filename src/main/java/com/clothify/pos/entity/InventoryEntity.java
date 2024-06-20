package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Inventory")
@Table(name = "Inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String productId;
    private String productName;
    private String category;
    private String supplierId;
    private Integer qtyOnHand;
    private Integer receivedQty;
    private Double unitPrice;
    private Double totalInventoryPrice;
}
