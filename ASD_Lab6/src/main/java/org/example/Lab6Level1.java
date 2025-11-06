package org.example;

import java.util.Random;

public class Lab6Level1 {

    // --- Генерація масиву ---
    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(10000); // випадкові числа 0..9999
        }
        return arr;
    }

    // --- Обмін елементів ---
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // --- Вибір опорного елемента (медіана трьох) ---
    public static int medianOfThree(int[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low] > arr[mid]) swap(arr, low, mid);
        if (arr[low] > arr[high]) swap(arr, low, high);
        if (arr[mid] > arr[high]) swap(arr, mid, high);

        swap(arr, mid, high - 1);
        return arr[high - 1];
    }

    // --- Розбиття масиву ---
    public static int partition(int[] arr, int low, int high) {
        int pivot = medianOfThree(arr, low, high);
        int i = low;
        int j = high - 1;

        while (true) {
            while (arr[++i] < pivot);
            while (arr[--j] > pivot);
            if (i >= j) break;
            swap(arr, i, j);
        }

        swap(arr, i, high - 1);
        return i;
    }

    // --- Швидке сортування ---
    public static void quickSort(int[] arr, int low, int high) {
        if (low + 10 <= high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        } else {
            insertionSort(arr, low, high);
        }
    }

    // --- Сортування вставками для малих підмасивів ---
    public static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // --- Обгортка для зручного виклику ---
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    // --- Вимір часу виконання алгоритму ---
    public static long measureTime(int[] arr) {
        int[] copy = arr.clone();
        long start = System.nanoTime();
        quickSort(copy);
        long end = System.nanoTime();
        return end - start;
    }

    // --- Обчислення середнього часу ---
    public static double averageTime(int size, int iterations) {
        double total = 0;
        for (int i = 0; i < iterations; i++) {
            int[] data = generateArray(size);
            total += measureTime(data);
        }
        return total / iterations;
    }

    // --- Тест ---
    public static void main(String[] args) {
        int N = 100;
        int N2 = N * N;
        int N3 = N * N * N;
        int iterations = 5;

        System.out.println("Алгоритм: QuickSort з медіаною трьох");
        System.out.println("Структура даних: одновимірний масив");
        System.out.println("------------------------------------");
        System.out.println("Розмір\tСередній час (нс)");

        double t1 = averageTime(N, iterations);
        double t2 = averageTime(N2, iterations);
        double t3 = averageTime(N3, iterations);

        System.out.printf("%-10d %.0f\n", N, t1);
        System.out.printf("%-10d %.0f\n", N2, t2);
        System.out.printf("%-10d %.0f\n", N3, t3);

        System.out.println("X — розмір масиву, Y — середній час виконання (нс).");
    }
}
