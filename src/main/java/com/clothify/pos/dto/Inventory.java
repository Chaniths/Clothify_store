package com.clothify.pos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
      private String productId;
      private String productName;
      private String category;
      private String supplierId;
      private Integer qtyOnHand;
      private Integer receivedQty;
      private Double unitPrice;
     private Double totalInventoryPrice;

}
