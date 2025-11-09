package org.example;

// 1 рівень
public class HashTableArray {
    private Vector[] table;
    private int size;

    public HashTableArray(int size) {
        this.size = size;
        table = new Vector[size];
    }

    private int hash(double key) {
        return Math.abs(Double.hashCode(key)) % size;
    }

    public boolean insert(Vector v) {
        int pos = hash(v.getX());
        if (table[pos] != null) {
            System.out.printf("Колізія у таблиці 1 (позиція %d вже зайнята)%n", pos);
            return false;
        }
        table[pos] = v;
        return true;
    }

    public void display() {
        System.out.println("\n===============================");
        System.out.println("=== 1 РІВЕНЬ — HashTableArray ===");
        System.out.println("===============================");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            if (table[i] == null) System.out.println("—");
            else System.out.println(table[i]);
        }
    }
}
