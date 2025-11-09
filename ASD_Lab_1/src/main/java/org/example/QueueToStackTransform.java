package org.example;

public class QueueToStackTransform {
    public void runTransformation() {
        ArrayQueue queue = new ArrayQueue(6);
        queue.addElementToQueue(3.7);
        queue.addElementToQueue(-1.5);
        queue.addElementToQueue(2.2);
        queue.addElementToQueue(0);
        queue.addElementToQueue(4.8);
        queue.addElementToQueue(-3.3);

        StackBinary stack = new StackBinary();

        double[] elements = queue.getElements();
        System.out.println("Переносимо додатні елементи до стеку (у двійковій системі):");
        for (double val : elements) {
            if (val > 0) {
                int rounded = (int) Math.round(val);
                String binary = Integer.toBinaryString(rounded);
                stack.push(binary);
                System.out.printf(" %.1f → %d → %s\n", val, rounded, binary);
            }
        }

        System.out.println("\nРезультат:");
        stack.display();
    }
}
