package com.attraya.stockservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_events")
public class OrderEvent {

    @Id
    private String orderId;
    private String message;
    private String status;
    private String orderName;
    private int orderQty;
    private Double orderPrice;

}