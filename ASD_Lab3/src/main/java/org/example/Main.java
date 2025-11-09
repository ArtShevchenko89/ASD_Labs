package org.example;

public class Main {
    public static void main(String[] args) {

        // 1 рівень
        System.out.println("\n--- Перший рівень (базове дерево) ---");
        StudentTreeBasic tree1 = new StudentTreeBasic();
        tree1.insert(new StudentNode("Іваненко", "Марія", 2, 101, 2100));
        tree1.insert(new StudentNode("Коваль", "Олег", 3, 87, 1800));
        tree1.insert(new StudentNode("Петренко", "Анна", 1, 150, 2500));
        tree1.insert(new StudentNode("Сидоренко", "Дмитро", 4, 120, 0));
        tree1.insert(new StudentNode("Мельник", "Ігор", 4, 90, 1500));
        tree1.display();


        // 2 рівень
        System.out.println("\n--- Другий рівень (паралельний пошук за номером квитка) ---");
        StudentTreeSearchable tree2 = new StudentTreeSearchable();
        tree2.insert(new StudentNode("Іваненко", "Марія", 2, 101, 2100));
        tree2.insert(new StudentNode("Коваль", "Олег", 3, 87, 1800));
        tree2.insert(new StudentNode("Петренко", "Анна", 1, 150, 2500));
        tree2.insert(new StudentNode("Мельник", "Ігор", 4, 90, 1500));
        tree2.insert(new StudentNode("Сидоренко", "Дмитро", 4, 120, 0));
        tree2.display();

        // Паралельний пошук студента за номером квитка
        tree2.searchAndDisplay(90);


        // 3 рівень
        System.out.println("\n--- Третій рівень ---");
        StudentTreeAdvanced tree3 = new StudentTreeAdvanced();
        tree3.insert(new StudentNode("Іваненко", "Марія", 2, 101, 2100));
        tree3.insert(new StudentNode("Коваль", "Олег", 3, 87, 1800));
        tree3.insert(new StudentNode("Петренко", "Анна", 1, 150, 2500));
        tree3.insert(new StudentNode("Мельник", "Ігор", 4, 90, 1500));
        tree3.insert(new StudentNode("Сидоренко", "Дмитро", 4, 120, 0));
        tree3.display();

        // Пошук студентів 4 курсу без стипендії
        System.out.println("\nСтуденти 4-го курсу, які не мають стипендії:");
        tree3.findNoScholarshipFourthCourse(tree3.getRoot());
    }
}
