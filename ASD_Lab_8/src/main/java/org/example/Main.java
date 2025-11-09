package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("1 — Рівень 1 (regex по файлу: табл. 8.1/13)");
            System.out.println("2 — Рівень 2 (switch-DFA: табл. 8.2/13)");
            System.out.println("3 — Рівень 3 (табличний DFA + split за { } !: табл. 8.2/13, 8.3/13)");
            System.out.println("0 — Вихід");
            System.out.print("Вибір: ");
            while (!sc.hasNextInt()) { System.out.print("0–3: "); sc.next(); }
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> new IdentifierRegexBasic().run();
                case 2 -> new IdentifierSwitchDFA().run();
                case 3 -> new IdentifierTableDFA().run();
                case 0 -> System.out.println("Вихід...");
                default -> System.out.println("Хибний вибір");
            }
        } while (choice != 0);
        sc.close();
    }
}
