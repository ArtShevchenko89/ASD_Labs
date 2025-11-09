package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Перший рівень: Черга (дійсні числа) ===");
        ArrayQueue queue = new ArrayQueue(6);
        queue.addElementToQueue(1.5);
        queue.addElementToQueue(-2.7);
        queue.addElementToQueue(3.9);
        queue.addElementToQueue(0);
        queue.addElementToQueue(4.2);
        queue.display();

        queue.deleteFromElementQueue();
        queue.display();

        System.out.println("\n=== Другий рівень: Стек (автоматичне двійкове представлення) ===");
        StackBinary stack = new StackBinary();
        stack.run();

        System.out.println("\n=== Третій рівень: Перенесення з черги у стек ===");
        QueueToStackTransform transform = new QueueToStackTransform();
        transform.runTransformation();
    }
}
