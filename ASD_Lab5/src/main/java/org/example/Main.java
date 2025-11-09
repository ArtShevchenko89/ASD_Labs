package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=============================");
            System.out.println("      Лабораторна №5");
            System.out.println("=============================");
            System.out.println("1 — Перший рівень (масив: видалити за прізвищем, якщо є борг)");
            System.out.println("2 — Другий рівень (BST: ключ = кількість заборгованостей)");
            System.out.println("3 — Третій рівень (BST: оптимізація/балансування)");
            System.out.println("0 — Вихід");
            System.out.print("Вибір: ");

            while (!sc.hasNextInt()) {
                System.out.print("Введіть число 0–3: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("\n=== ПЕРШИЙ РІВЕНЬ ===");
                    new StudentSearchLevel1().run();      // масив + видалення за умовою
                }
                case 2 -> {
                    System.out.println("\n=== ДРУГИЙ РІВЕНЬ ===");
                    new StudentTreeLevel2().run();         // BST з ключем debts + пошук
                }
                case 3 -> {
                    System.out.println("\n=== ТРЕТІЙ РІВЕНЬ ===");
                    new StudentTreeLevel3().run();         // балансування/оптимізація BST
                }
                case 0 -> System.out.println("Вихід...");
                default -> System.out.println("Невірний вибір.");
            }
        } while (choice != 0);

        sc.close();
    }
}
