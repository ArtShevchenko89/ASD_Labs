package org.example;

public class Student {
    String lastName;
    String firstName;
    int course;
    int debts;

    public Student(String lastName, String firstName, int course, int debts) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.course = course;
        this.debts = debts;
    }

    public void display() {
        System.out.printf("%-12s %-10s %-8d %-6d%n",
                lastName, firstName, course, debts);
    }
}
