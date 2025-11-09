package org.example;

import java.util.Scanner;
// 2 рівень
public class IdentifierSwitchDFA {

    private enum State { S0, S1, S2, S3, S4, S5, DEAD }

    private static boolean isUpperAZ(char c) { return c >= 'A' && c <= 'Z'; }
    private static boolean isDigit(char c) { return c >= '0' && c <= '9'; }
    private static boolean isGroup2Char(char c) {
        return c != '^' && !isDigit(c) && c != 'A' && c != 'Z';
    }

    public boolean validate(String s) {
        State st = State.S0;
        int i = 0, n = s.length();

        while (true) {
            char c = (i < n ? s.charAt(i) : '\0');

            switch (st) {
                case S0 -> {
                    if (i >= n || c != '^') return false;
                    i++; st = State.S1;
                }
                case S1 -> {
                    if (i >= n || !isUpperAZ(c)) return false;
                    do { i++; } while (i < n && isUpperAZ(s.charAt(i)));
                    st = State.S2;
                }
                case S2 -> {
                    if (i >= n || s.charAt(i) != '^') return false;
                    i++; st = State.S3;
                }
                case S3 -> {
                    if (i >= n || s.charAt(i) != '*') return false;
                    i++; st = State.S4;
                }
                case S4 -> {
                    if (i >= n || s.charAt(i) != '^') return false;
                    i++; st = State.S5;
                }
                case S5 -> {
                    if (i >= n || !isGroup2Char(s.charAt(i))) return false;
                    do { i++; } while (i < n && isGroup2Char(s.charAt(i)));
                    if (i >= n || s.charAt(i) != '^') return false;
                    i++;
                    return i == n;
                }
                default -> { return false; }
            }
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Вводьте рядки для перевірки (порожній — кінець):");
        while (true) {
            String s = sc.nextLine().trim();
            if (s.isEmpty()) break;
            System.out.println(validate(s) ? "OK" : "NO");
        }
    }
}
