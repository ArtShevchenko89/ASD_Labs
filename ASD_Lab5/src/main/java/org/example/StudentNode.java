package org.example;

public class StudentNode {
    String lastName;
    String firstName;
    int course;
    int debts;
    StudentNode left, right;

    public StudentNode(String l, String f, int c, int d) {
        lastName = l;
        firstName = f;
        course = c;
        debts = d;
    }

    public void display() {
        System.out.printf("%-12s %-10s %-8d %-6d%n", lastName, firstName, course, debts);
    }
}
