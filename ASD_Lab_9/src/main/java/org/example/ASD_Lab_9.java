package org.example;

public class ASD_Lab_9 {
    public static void main(String[] args) {
        long result = factorial(10) * factorial(3);
        System.out.println("Кількість способів: " + result);
    }

    private static long factorial(int n) {
        long f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }
}
