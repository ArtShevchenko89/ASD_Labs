package org.example;

import java.util.Scanner;

// 2 рівень
public class IntegrationInteractive {
    public void run() {
        Scanner sc = new Scanner(System.in);
        double a = 1.0, b = 2.0, h = 0.2;

        System.out.println("Оберіть метод:");
        System.out.println("1 — прямокутників");
        System.out.println("2 — трапецій");
        System.out.println("3 — Сімпсона");
        System.out.print("Ваш вибір: ");
        int choice = sc.nextInt();

        double result = switch (choice) {
            case 1 -> IntegrationMethods.rectangle(a, b, h);
            case 2 -> IntegrationMethods.trapezoid(a, b, h);
            case 3 -> IntegrationMethods.simpson(a, b, h);
            default -> Double.NaN;
        };

        if (Double.isNaN(result)) {
            System.out.println("Невірний вибір.");
            return;
        }

        System.out.printf("Результат: %.6f%n", result);
        System.out.printf("Аналітичне значення: %.6f%n",
                IntegrationMethods.analytical(a, b));
    }
}
