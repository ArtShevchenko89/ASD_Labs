package org.example;

import java.util.Random;

// 2 рівень
public class SeveralAlgorithms {

    private int[] generate(int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = rnd.nextInt(10000);
        return arr;
    }

    private int[] cloneArray(int[] src) {
        int[] copy = new int[src.length];
        System.arraycopy(src, 0, copy, 0, src.length);
        return copy;
    }

    private void measureAll(int n) {
        int[] base = generate(n);

        int[] a1 = cloneArray(base);
        int[] a2 = cloneArray(base);
        int[] a3 = cloneArray(base);

        long start, end;

        start = System.nanoTime();
        SortAlgorithms.bubbleSort(a1);
        end = System.nanoTime();
        double t1 = (end - start) / 1e6;

        start = System.nanoTime();
        SortAlgorithms.insertionSort(a2);
        end = System.nanoTime();
        double t2 = (end - start) / 1e6;

        start = System.nanoTime();
        SortAlgorithms.quickSortMedianOfThree(a3, 0, a3.length - 1);
        end = System.nanoTime();
        double t3 = (end - start) / 1e6;

        System.out.printf("%-10d %-10.2f %-10.2f %-10.2f\n", n, t1, t2, t3);
    }

    public void run() {
        System.out.printf("%-10s %-10s %-10s %-10s\n",
                "N", "Bubble", "Insertion", "Quick(Median3)");
        int[] sizes = {100, 300, 1000, 3000, 10000};
        for (int n : sizes) measureAll(n);
    }
}
