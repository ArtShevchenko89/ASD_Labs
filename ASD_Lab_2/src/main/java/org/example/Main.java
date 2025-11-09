package org.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rnd = new Random(42); // фіксовані значення для стабільності виводу

        // 1 рівень
        HashTableArray ht1 = new HashTableArray(8);
        for (int i = 0; i < 5; i++) {
            Vector v = new Vector(1 + rnd.nextDouble() * 9, rnd.nextDouble() * Math.PI);
            ht1.insert(v);
        }
        ht1.display();

        // 2 рівень
        HashTableChained ht2 = new HashTableChained(5);
        for (int i = 0; i < 8; i++) {
            Vector v = new Vector(1 + rnd.nextDouble() * 9, rnd.nextDouble() * Math.PI);
            ht2.insert(v);
        }
        ht2.display();

        // 3 рівень
        HashTableDynamic ht3 = new HashTableDynamic(4);
        for (int i = 0; i < 10; i++) {
            Vector v = new Vector(1 + rnd.nextDouble() * 9, rnd.nextDouble() * Math.PI);
            ht3.insert(v);
        }

        ht3.display();

        // 🔹 Видалення за критерієм Y < 2.0
        System.out.println("\nВидаляємо елементи, де Y < 2.0:");
        ht3.removeByY(2.0);
        ht3.display();
    }
}
