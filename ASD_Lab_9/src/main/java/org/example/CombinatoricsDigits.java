package org.example;

import java.math.BigInteger;

// 2 рівень
public class CombinatoricsDigits {

    public void run() {
        int k = 5;
        int n = 15;

        BigInteger result = BigInteger.valueOf(k).pow(n);

        System.out.println("=== Лабораторна 9 — Другий рівень ===");
        System.out.println("Кількість способів розміщення: " + result);
        System.out.println("Формула: 5^15");
    }
}
