package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class StudentSearchLevel1 {

    public void run() {
        Scanner sc = new Scanner(System.in);

        Student[] students = {
                new Student("Іваненко", "Марія", 2, 0),
                new Student("Коваль", "Олег", 3, 2),
                new Student("Петренко", "Анна", 1, 1),
                new Student("Сидоренко", "Дмитро", 4, 0),
                new Student("Мельник", "Ігор", 2, 3)
        };

        System.out.println("=== Список студентів до видалення ===");
        for (Student s : students) s.display();

        System.out.print("\nВведіть прізвище студента для перевірки/видалення: ");
        String target = sc.nextLine().trim();

        students = deleteByCondition(students, target);

        System.out.println("\n=== Список після перевірки ===");
        for (Student s : students) s.display();
    }

    private Student[] deleteByCondition(Student[] arr, String lastName) {
        boolean removed = Arrays.stream(arr)
                .anyMatch(s -> s.lastName.equalsIgnoreCase(lastName) && s.debts > 0);

        if (removed)
            System.out.println("→ Студента \"" + lastName + "\" видалено, бо має заборгованість.");
        else
            System.out.println("→ Студента \"" + lastName + "\" не видалено (немає боргів або не знайдено).");

        return Arrays.stream(arr)
                .filter(s -> !(s.lastName.equalsIgnoreCase(lastName) && s.debts > 0))
                .toArray(Student[]::new);
    }
}
