package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
// 1 рівень
public class IdentifierRegexBasic {

    private static final String INPUT_FILE = "input.txt";
    private static final Pattern PATTERN =
            Pattern.compile("^\\^[A-Z]+\\^\\*\\^[A-Z]+\\^$");

    public void run() {
        List<String> ok = new ArrayList<>();
        int total = 0;

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(INPUT_FILE), StandardCharsets.UTF_8))) {

            String line;
            while ((line = br.readLine()) != null) {
                String s = line.trim();
                if (s.isEmpty()) continue;
                total++;
                if (PATTERN.matcher(s).matches()) ok.add(s);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання " + INPUT_FILE + ": " + e.getMessage());
            return;
        }

        System.out.println("Вхідних рядків: " + total);
        System.out.println("Збігів: " + ok.size());
        ok.forEach(System.out::println);
    }
}
