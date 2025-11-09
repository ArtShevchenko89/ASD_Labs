package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nОберіть рівень виконання:");
            System.out.println("1 — Перший рівень (Counting Sort)");
            System.out.println("2 — Другий рівень (Radix Sort)");
            System.out.println("3 — Третій рівень (Quick Sort)");
            System.out.println("0 — Вихід");
            System.out.print("Вибір: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> new CountingSortLevel().run();
                case 2 -> new RadixSortLevel().run();
                case 3 -> new QuickSortLevel().run();
                case 0 -> System.out.println("Завершення роботи...");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }
        } while (choice != 0);

        sc.close();
    }
}
