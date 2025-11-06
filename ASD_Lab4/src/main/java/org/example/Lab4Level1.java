package org.example;

import java.util.Random;

// Клас Студент
class Student {
    String lastName;
    String firstName;
    int cityCode; // тризначне число
    String phone;

    public Student(String lastName, String firstName, int cityCode, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.cityCode = cityCode;
        this.phone = phone;
    }

    public void display() {
        System.out.printf("%-12s %-10s %-10d %-12s\n", lastName, firstName, cityCode, phone);
    }
}

// Клас із методом сортування
class CountingSort {
    public static void sort(Student[] arr) {
        int n = arr.length;
        int max = getMaxCityCode(arr);
        int min = getMinCityCode(arr);
        int range = max - min + 1;

        int[] count = new int[range];
        Student[] output = new Student[n];

        // Підрахунок кількості кожного коду міста
        for (Student student : arr) {
            count[student.cityCode - min]++;
        }

        // Зміна count[i] так, щоб зберігати позиції елементів
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Побудова вихідного масиву
        for (int i = n - 1; i >= 0; i--) {
            Student student = arr[i];
            output[count[student.cityCode - min] - 1] = student;
            count[student.cityCode - min]--;
        }

        // Копіювання у вихідний масив
        System.arraycopy(output, 0, arr, 0, n);
    }

    private static int getMaxCityCode(Student[] arr) {
        int max = arr[0].cityCode;
        for (Student s : arr) {
            if (s.cityCode > max) max = s.cityCode;
        }
        return max;
    }

    private static int getMinCityCode(Student[] arr) {
        int min = arr[0].cityCode;
        for (Student s : arr) {
            if (s.cityCode < min) min = s.cityCode;
        }
        return min;
    }
}

// Основний клас
public class Lab4Level1 {
    public static void main(String[] args) {
        Student[] students = {
                new Student("Shevchenko", "Artem", 803, "345678"),
                new Student("Ivanov", "Petro", 701, "987654"),
                new Student("Koval", "Oleg", 805, "765432"),
                new Student("Bondar", "Nina", 704, "123456"),
                new Student("Melnyk", "Anna", 702, "654321")
        };

        System.out.println("До сортування:");
        printArray(students);

        CountingSort.sort(students);

        System.out.println("\nПісля сортування (за кодом міста, зростанням):");
        printArray(students);
    }

    private static void printArray(Student[] arr) {
        System.out.printf("%-12s %-10s %-10s %-12s\n", "Прізвище", "Ім'я", "Код міста", "Телефон");
        for (Student s : arr) {
            s.display();
        }
    }
}
