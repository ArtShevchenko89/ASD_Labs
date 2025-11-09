package org.example;

// 2 рівень
public class RadixSortLevel {
    public void run() {
        Student[] students = {
                new Student("Іваненко", "Марія", 305, "305-44-22"),
                new Student("Коваль", "Олег", 210, "210-13-37"),
                new Student("Мельник", "Ігор", 410, "410-77-66"),
                new Student("Сидоренко", "Анна", 150, "150-33-21"),
                new Student("Петренко", "Юлія", 270, "270-88-10")
        };

        System.out.println("До сортування:");
        for (Student s : students) s.display();

        SortAlgorithms.radixSort(students);

        System.out.println("\nПісля сортування (Radix Sort, за кодом міста):");
        for (Student s : students) s.display();
    }
}
