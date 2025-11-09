package org.example;

public class StudentNode {
    String lastName;
    String firstName;
    int course;
    long studentId;      // ключ
    double scholarship;

    StudentNode left;
    StudentNode right;

    public StudentNode(String lastName, String firstName, int course, long studentId, double scholarship) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.course = course;
        this.studentId = studentId;
        this.scholarship = scholarship;
    }

    public void display() {
        System.out.printf("%-10s %-10s %-6d %-10d %-8.2f\n",
                lastName, firstName, course, studentId, scholarship);
    }
}
