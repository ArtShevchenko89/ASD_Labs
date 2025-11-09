package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
// 3 рівень
public class IdentifierTableDFA {

    private enum State { S0, S1, S2, S3, S4, S5, DEAD }

    private static final int CC_CARET = 0;
    private static final int CC_STAR = 1;
    private static final int CC_AZ = 2;
    private static final int CC_DIGIT = 3;
    private static final int CC_G2OK = 4;
    private static final int CC_OTHER = 5;

    private static boolean isUpperAZ(char c) { return c >= 'A' && c <= 'Z'; }
    private static boolean isDigit(char c) { return c >= '0' && c <= '9'; }
    private static boolean isGroup2Char(char c) { return c != '^' && !isDigit(c) && c != 'A' && c != 'Z'; }

    private static int clazz(char c) {
        if (c == '^') return CC_CARET;
        if (c == '*') return CC_STAR;
        if (isUpperAZ(c)) return CC_AZ;
        if (isDigit(c)) return CC_DIGIT;
        if (isGroup2Char(c)) return CC_G2OK;
        return CC_OTHER;
    }

    private static final State D = State.DEAD;
    private static final State[][] NEXT = new State[][]{
        //             ^        *        A-Z      DIGIT    G2OK     OTHER
        /* S0 */ { State.S1,    D,       D,       D,       D,       D },
        /* S1 */ { D,           D,       State.S1, D,      D,       D },   // [A-Z]+
        /* S2 */ { D,           State.S3, D,       D,      D,       D },   // після '^' очікуємо '*'
        /* S3 */ { State.S4,    D,       D,       D,       D,       D },   // після '*' очікуємо '^'
        /* S4 */ { D,           D,       D,       D,      State.S5, D },   // [^AZ^\d]+
        /* S5 */ { D,           D,       State.S5, D,      State.S5, D }   // накопичення другої групи (до фінальної '^')
    };

    public boolean validate(String s) {
        State st = State.S0;
        int i = 0, n = s.length();

        if (n == 0 || NEXT[st.ordinal()][clazz(s.charAt(0))] != State.S1) return false;
        st = State.S1; i++;

        if (i >= n || !isUpperAZ(s.charAt(i))) return false;
        while (i < n && isUpperAZ(s.charAt(i))) i++;

        if (i >= n || s.charAt(i) != '^') return false;
        st = State.S2; i++;

        if (i >= n || s.charAt(i) != '*') return false;
        st = State.S3; i++;

        if (i >= n || s.charAt(i) != '^') return false;
        st = State.S4; i++;

        if (i >= n || !isGroup2Char(s.charAt(i))) return false;
        while (i < n && isGroup2Char(s.charAt(i))) i++;
        st = State.S5;

        if (i >= n || s.charAt(i) != '^') return false;
        i++;
        return i == n;
    }

    public void run() {
        final String INPUT = "input_level3.txt";
        final String SPLIT_REGEX = "[\\{\\}!]+";
        int total = 0, ok = 0, bad = 0;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(INPUT), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                for (String token : line.split(SPLIT_REGEX)) {
                    String s = token.trim();
                    if (s.isEmpty()) continue;
                    total++;
                    if (validate(s)) ok++; else bad++;
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
            return;
        }

        System.out.println("=== Рівень 3: підсумок ===");
        System.out.println("Слів опрацьовано: " + total);
        System.out.println("Коректних: " + ok);
        System.out.println("Некоректних: " + bad);
    }
}
