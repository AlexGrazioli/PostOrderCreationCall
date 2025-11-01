package com.example;

import com.example.orders.controller.OrderController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderController.class, args);
    }
}
