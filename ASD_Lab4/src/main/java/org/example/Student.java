package org.example;

public class Student {
    String lastName;
    String firstName;
    int cityCode;
    String phone;

    public Student(String lastName, String firstName, int cityCode, String phone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.cityCode = cityCode;
        this.phone = phone;
    }

    public void display() {
        System.out.printf("%-12s %-10s %-10d %-12s%n",
                lastName, firstName, cityCode, phone);
    }
}
