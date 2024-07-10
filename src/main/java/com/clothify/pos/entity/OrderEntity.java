package com.clothify.pos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Orders")
@Table(name = "Orders")
public class OrderEntity {
    @Id
    @Column(unique = true)
    private String orderId;
    private String customerId;
    private String customerName;
    private String contact;
    private Date date;
    private Double total;
    private Boolean status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetailEntity> orderDetails;
}