package com.example.orders.service;

import com.example.orders.exceptions.OrderPlacementError;
import com.example.orders.model.Order;
import com.example.orders.utils.RandomUtils;
import org.springframework.stereotype.Service;

@Service
public class StockExchangeService {

    public void placeOrder(Order order) throws OrderPlacementError {

        if (RandomUtils.tenPercentTrue()) {
            throw new OrderPlacementError("Failed to place the order at the stock exchange");
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new OrderPlacementError("Order placement interrupted");
        }
    }
}
