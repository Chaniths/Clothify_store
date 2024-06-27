package com.clothify.pos.dto;

import javafx.collections.ObservableList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String customerId;
    private String customerName;
    private String contact;
    private LocalDateTime date;
    private List<OrderDetail> orderDetails;
    private Double total;
    private Boolean status;

}
