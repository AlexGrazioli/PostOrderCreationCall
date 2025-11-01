package com.example.orders.model;

import java.time.LocalDateTime;

public class OrderResponse {

    private Long id;
    private String instrument;
    private String type;
    private Integer quantity;
    private String side;
    private Float limitPrice;
    private String status;
    private LocalDateTime createdAt;

    public OrderResponse(Order order) {
        this.id = order.getId();
        this.instrument = order.getInstrument();
        this.type = order.getType().toString();
        this.quantity = order.getQuantity();
        this.side = order.getSide().toString();
        this.limitPrice = order.getLimitPrice();
        this.status = order.getOrderStatus().toString();
        this.createdAt = order.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getInstrument() {
        return instrument;
    }

    public String getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getSide() {
        return side;
    }

    public Float getLimitPrice() {
        return limitPrice;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
