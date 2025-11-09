package org.example;

import java.util.Random;

public class StackBinary {
    private static class Node {
        String value;
        Node next;
        Node(String value) { this.value = value; }
    }

    private Node top;
    private int size;


    public void push(String binaryValue) {
        Node node = new Node(binaryValue);
        node.next = top;
        top = node;
        size++;
    }

    public String deleteTopElement() {
        String val = top.value;
        top = top.next;
        size--;
        return val;
    }

    public void display() {
        System.out.print("Стек (двійкові числа): ");
        Node cur = top;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void run() {
        Random rnd = new Random();
        System.out.println("=== Другий рівень: Стек (генерація чисел у двійковій системі) ===");

        for (int i = 0; i < 5; i++) {
            int num = rnd.nextInt(20) + 1; // генеруємо число 1..20
            String binary = Integer.toBinaryString(num);
            push(binary);
            System.out.printf("Додано: %d → %s\n", num, binary);
        }

        System.out.println("\nПоточний вміст стеку:");
        display();

        System.out.println("\nВидаляємо верхній елемент...");
        deleteTopElement();
        display();
    }
}
