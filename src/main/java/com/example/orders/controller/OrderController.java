package com.example.orders.controller;

import com.example.orders.model.Order;
import com.example.orders.model.OrderRequest;
import com.example.orders.model.OrderResponse;
import com.example.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request) throws InterruptedException {
        try {
            Order order = orderService.createOrder(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new OrderResponse(order));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Internal server error while placing the order");
        }
    }
}
