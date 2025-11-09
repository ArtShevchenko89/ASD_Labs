package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1 — Перший рівень (QuickSort з медіаною трьох)");
            System.out.println("2 — Другий рівень (Порівняння алгоритмів)");
            System.out.println("0 — Вихід");
            System.out.print("Вибір: ");

            while (!sc.hasNextInt()) {
                System.out.print("Введіть число (0–2): ");
                sc.next();
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n=== ПЕРШИЙ РІВЕНЬ ===");
                    new QuickSort().run();
                }
                case 2 -> {
                    System.out.println("\n=== ДРУГИЙ РІВЕНЬ ===");
                    new SeveralAlgorithms().run();
                }
                case 0 -> System.out.println("Вихід з програми...");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }

        } while (choice != 0);

        sc.close();
    }
}
