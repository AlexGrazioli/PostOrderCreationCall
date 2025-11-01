package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.model.OrderRequest;
import com.example.orders.model.OrderStatus;
import com.example.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderPlacementService orderPlacementService;

    public OrderService(OrderRepository orderRepository, OrderPlacementService orderPlacementService) {
        this.orderRepository = orderRepository;
        this.orderPlacementService = orderPlacementService;
    }

    public Order createOrder(OrderRequest request) {
        if ("limit".equals(request.getType()) && request.getLimitPrice() == null) {
            throw new IllegalArgumentException("Limit price is required for limit orders");
        }

        Order order = new Order();
        order.setInstrument(request.getInstrument());
        order.setType(request.getType());
        order.setQuantity(request.getQuantity());
        order.setSide(request.getSide());
        order.setLimitPrice(request.getLimitPrice());
        order.setOrderStatus(OrderStatus.PENDING);

        Order savedOrder = orderRepository.save(order);

        orderPlacementService.placeOrderAsync(savedOrder.getId());

        return savedOrder;
    }
}
