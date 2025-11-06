package org.example;

import java.util.Random;

class Vector {
    private double r;      // модуль
    private double theta;  // кут у радіанах

    public Vector(double r, double theta) {
        this.r = r;
        this.theta = theta;
    }

    public double getX() {
        return r * Math.cos(theta);
    }

    public double getY() {
        return r * Math.sin(theta);
    }

    public void display() {
        System.out.printf("Vector: r=%.2f, theta=%.2f rad, x=%.2f, y=%.2f\n",
                r, theta, getX(), getY());
    }
}

class HashTable {
    private Vector[] table;
    private int size;

    public HashTable(int size) {
        this.size = size;
        table = new Vector[size];
    }

    // Метод хешування (ділення), завжди повертає індекс 0..size-1
    private int hash(double key) {
        int pos = ((int)key % size + size) % size; // виправлення від’ємних значень
        return pos;
    }

    // Вставка елемента, повертає true якщо успішно
    public boolean insert(Vector v) {
        int key = (int)v.getX();
        int pos = hash(key);

        if (table[pos] != null) {
            // Для першого рівня колізій не повинно бути
            return false;
        }

        table[pos] = v;
        return true;
    }

    // Виведення хеш-таблиці
    public void display() {
        for (int i = 0; i < size; i++) {
            System.out.printf("Position %d: ", i);
            if (table[i] != null) {
                System.out.printf("Key=%.0f, ", table[i].getX());
                table[i].display();
            } else {
                System.out.println("empty");
            }
        }
    }
}

public class Lab2Level1 {
    public static void main(String[] args) {
        int tableSize = 10;
        HashTable hashTable = new HashTable(tableSize);
        Random rand = new Random();

        // Додаємо елементи, щоб не було колізій
        for (int i = 0; i < tableSize; i++) {
            Vector v;
            do {
                double r = 1 + rand.nextDouble() * 10;         // r > 0
                double theta = rand.nextDouble() * Math.PI;    // 0..π
                v = new Vector(r, theta);
            } while (!hashTable.insert(v)); // повторюємо, якщо позиція зайнята
        }

        hashTable.display();
    }
}
