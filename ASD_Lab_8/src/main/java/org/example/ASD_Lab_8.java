package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class ASD_Lab_8 {

    private static final String INPUT_FILE = "input.txt";

    private static final Pattern PATTERN = Pattern.compile("^\\^[A-Z]+\\^\\*\\^[A-Z]+\\^$");

    public static void main(String[] args) {
        System.out.println("Формат слова: ^ABC^*^XYZ^");
        System.out.println("-------------------------------------------");
        System.out.printf("%-25s %-25s%n", "Слово", "Відповідає шаблону");

        int total = 0;
        int matched = 0;

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(INPUT_FILE), StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                total++;
                boolean ok = PATTERN.matcher(line).matches();
                System.out.printf("%-25s %-25s%n", line, ok ? "Так" : "Ні");
                if (ok) matched++;
            }

        } catch (IOException e) {
            System.out.println("Помилка при читанні файлу: " + e.getMessage());
            return;
        }

        System.out.println("-------------------------------------------");
        System.out.println("Кількість оброблених рядків: " + total);
        System.out.println("Кількість відповідних шаблону: " + matched);
    }
}
