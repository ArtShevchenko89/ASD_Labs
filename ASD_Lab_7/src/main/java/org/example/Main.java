package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1 — Перший рівень (усі методи одразу)");
            System.out.println("2 — Другий рівень (вибір користувачем)");
            System.out.println("3 — Третій рівень (порівняння та похибка)");
            System.out.println("0 — Вихід");
            System.out.print("Вибір: ");

            while (!sc.hasNextInt()) {
                System.out.print("Введіть число (0–3): ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> new IntegrationBasic().run();
                case 2 -> new IntegrationInteractive().run();
                case 3 -> new IntegrationComparison().run();
                case 0 -> System.out.println("Вихід з програми...");
                default -> System.out.println("Невірний вибір, спробуйте ще раз.");
            }

        } while (choice != 0);

        sc.close();
    }
}
