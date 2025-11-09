package org.example;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTableDynamic {
    private LinkedList<Vector>[] table;
    private int size;
    private int count;

    @SuppressWarnings("unchecked")
    public HashTableDynamic(int initialSize) {
        size = initialSize;
        table = new LinkedList[size];
        for (int i = 0; i < size; i++)
            table[i] = new LinkedList<>();
        count = 0;
    }

    private int hash(double key) {
        return Math.abs(Double.hashCode(key)) % size;
    }

    private double loadFactor() {
        return (double) count / size;
    }

    public void insert(Vector v) {
        if (loadFactor() > 0.75)
            resize(size * 2);

        int pos = hash(v.getX());
        table[pos].add(v);
        count++;
    }

    // 🔹 Видалення елементів, у яких Y < threshold
    public void removeByY(double threshold) {
        int removed = 0;
        for (LinkedList<Vector> bucket : table) {
            Iterator<Vector> it = bucket.iterator();
            while (it.hasNext()) {
                Vector v = it.next();
                if (v.getY() < threshold) {
                    it.remove();
                    count--;
                    removed++;
                }
            }
        }
        System.out.printf("Видалено %d елемент(ів), де Y < %.2f%n", removed, threshold);
    }

    @SuppressWarnings("unchecked")
    private void resize(int newSize) {
        LinkedList<Vector>[] old = table;
        table = new LinkedList[newSize];
        for (int i = 0; i < newSize; i++)
            table[i] = new LinkedList<>();
        size = newSize;
        int oldCount = count;
        count = 0;
        for (LinkedList<Vector> bucket : old)
            for (Vector v : bucket)
                insert(v);
    }

    public void display() {
        System.out.println("\n===============================");
        System.out.println("=== 3 РІВЕНЬ — HashTableDynamic ===");
        System.out.println("===============================");
        for (int i = 0; i < size; i++) {
            System.out.print(i + ": ");
            if (table[i].isEmpty())
                System.out.println("—");
            else {
                for (Vector v : table[i])
                    System.out.print("[" + v + "] ");
                System.out.println();
            }
        }
        System.out.printf("Розмір таблиці: %d, Кількість елементів: %d, Навантаження: %.2f%n",
                size, count, loadFactor());
    }
}
