package org.example;

import java.util.Random;

// 1 рівень
public class QuickSort {

    private int[] generate(int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = rnd.nextInt(10000);
        return arr;
    }

    private long measure(int n) {
        int[] arr = generate(n);
        long start = System.nanoTime();
        SortAlgorithms.quickSortMedianOfThree(arr, 0, arr.length - 1);
        return System.nanoTime() - start;
    }

    public void run() {
        int[] sizes = {32, 100, 320, 1000, 3200, 10000};
        System.out.printf("%-10s %-15s\n", "N", "Time (ms)");
        for (int n : sizes) {
            double ms = measure(n) / 1e6;
            System.out.printf("%-10d %-15.3f\n", n, ms);
        }
    }
}
