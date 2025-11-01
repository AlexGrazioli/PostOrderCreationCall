    package com.example.orders.exceptions;

    public class OrderPlacementError extends RuntimeException {
        public OrderPlacementError(String message) {
            super(message);
        }
    }
