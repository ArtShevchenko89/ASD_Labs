package org.example;

import java.util.LinkedList;

// 2 рівень
public class HashTableChained {
    private LinkedList<Vector>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTableChained(int size) {
        this.size = size;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++)
            table[i] = new LinkedList<>();
    }

    private int hash(double key) {
        return Math.abs(Double.hashCode(key)) % size;
    }

    public void insert(Vector v) {
        int pos = hash(v.getX());
        table[pos].add(v);
    }

    public void display() {
        System.out.println("\n===============================");
        System.out.println("=== 2 РІВЕНЬ — HashTableChained ===");
        System.out.println("===============================");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            if (table[i].isEmpty()) System.out.println("—");
            else {
                for (Vector v : table[i])
                    System.out.print("[" + v + "] ");
                System.out.println();
            }
        }
    }
}
