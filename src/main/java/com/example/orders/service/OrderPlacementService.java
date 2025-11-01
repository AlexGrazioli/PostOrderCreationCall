package com.example.orders.service;

import com.example.orders.exceptions.OrderPlacementError;
import com.example.orders.model.Order;
import com.example.orders.model.OrderStatus;
import com.example.orders.repository.OrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderPlacementService {
    private final StockExchangeService stockExchangeService;
    private final OrderRepository orderRepository;
    private static final int MAX_RETRIES = 3;

    public OrderPlacementService(StockExchangeService stockExchangeService, OrderRepository orderRepository) {
        this.stockExchangeService = stockExchangeService;
        this.orderRepository = orderRepository;
    }

    @Async("orderPlacementExecutor")
    public void placeOrderAsync(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            return;
        }
        boolean placed = false;

        while(order.getRetryCount() < MAX_RETRIES && !placed) {
            try {
                stockExchangeService.placeOrder(order);
                order.setOrderStatus(OrderStatus.PLACED);
                order.setPlacedAt(LocalDateTime.now());
                orderRepository.save(order);
            } catch (OrderPlacementError e) {
                order.setRetryCount(order.getRetryCount() + 1);
                orderRepository.save(order);

                if (order.getRetryCount() >= MAX_RETRIES) {
                    order.setOrderStatus(OrderStatus.FAILED);
                    orderRepository.save(order);
                }

                try {
                    Thread.sleep(1000L * order.getRetryCount());
                }catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

    }
}
