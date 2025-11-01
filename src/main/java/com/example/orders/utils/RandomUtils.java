package com.example.orders.utils;

import java.util.Random;

public class RandomUtils {

    private static final Random random = new Random();

    public static boolean tenPercentTrue() {
        return randomBoolean(0.10);
    }

    private static boolean randomBoolean(double trueProbability) {
        return random.nextDouble() < trueProbability;
    }
}
