package org.example;

class Student {
    String lastName;
    String firstName;
    int cityCode;
    boolean hasHomePhone;
    int course;
    String city;

    public Student(String lastName, String firstName, int cityCode, String city, boolean hasHomePhone, int course) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.cityCode = cityCode;
        this.city = city;
        this.hasHomePhone = hasHomePhone;
        this.course = course;
    }

    public void display() {
        System.out.printf("%-10d %-12s %-10s %-12s %-8s %-6d\n",
                cityCode, lastName, firstName, city,
                hasHomePhone ? "так" : "ні", course);
    }
}

class StudentArray {
    private Student[] students;
    private int size;

    public StudentArray(int capacity) {
        students = new Student[capacity];
        size = 0;
    }

    public void addStudent(Student s) {
        int i = size - 1;
        while (i >= 0 && students[i].cityCode > s.cityCode) {
            students[i + 1] = students[i];
            i--;
        }
        students[i + 1] = s;
        size++;
    }

    public void printAll() {
        System.out.printf("%-10s %-12s %-10s %-12s %-8s %-6s\n",
                "Код", "Прізвище", "Ім'я", "Місто", "Телефон", "Курс");
        for (int i = 0; i < size; i++)
            students[i].display();
    }

    public void removeLvivNoPhone() {
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            Student s = students[i];
            if (!(s.city.equalsIgnoreCase("Львів") && !s.hasHomePhone && s.course == 1)) {
                students[newSize++] = s;
            }
        }
        size = newSize;
    }
}

public class Lab5Level1 {
    public static void main(String[] args) {
        StudentArray arr = new StudentArray(30);

        arr.addStudent(new Student("Shevchenko", "Artem", 703, "Львів", false, 1));
        arr.addStudent(new Student("Ivanov", "Petro", 701, "Київ", true, 2));
        arr.addStudent(new Student("Koval", "Oleg", 705, "Львів", true, 3));
        arr.addStudent(new Student("Bondar", "Nina", 702, "Львів", false, 1));
        arr.addStudent(new Student("Melnyk", "Anna", 701, "Одеса", true, 4));
        arr.addStudent(new Student("Zhuk", "Oksana", 706, "Харків", true, 1));
        arr.addStudent(new Student("Lysenko", "Ira", 703, "Львів", false, 1));
        arr.addStudent(new Student("Kryvonis", "Andriy", 704, "Київ", true, 2));
        arr.addStudent(new Student("Tkachenko", "Olga", 702, "Львів", true, 1));
        arr.addStudent(new Student("Petrenko", "Roman", 707, "Львів", false, 1));
        arr.addStudent(new Student("Bila", "Nadia", 701, "Київ", true, 3));
        arr.addStudent(new Student("Horobets", "Yulia", 704, "Львів", false, 1));
        arr.addStudent(new Student("Tymchuk", "Denys", 703, "Одеса", true, 2));
        arr.addStudent(new Student("Sydorenko", "Taras", 702, "Львів", false, 1));
        arr.addStudent(new Student("Danylko", "Maxym", 705, "Львів", true, 2));
        arr.addStudent(new Student("Martyn", "Oleh", 706, "Львів", false, 1));
        arr.addStudent(new Student("Kuchma", "Vira", 701, "Київ", true, 1));
        arr.addStudent(new Student("Zelena", "Olena", 702, "Одеса", true, 2));
        arr.addStudent(new Student("Lev", "Ivan", 703, "Львів", false, 1));
        arr.addStudent(new Student("Hrytsenko", "Yurii", 704, "Львів", true, 3));

        System.out.println("До обробки:");
        arr.printAll();

        arr.removeLvivNoPhone();

        System.out.println("\nПісля видалення студентів 1 курсу зі Львова без телефону:");
        arr.printAll();
    }
}
