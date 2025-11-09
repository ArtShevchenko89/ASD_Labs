package org.example;

public class ArrayQueue {
    private final double[] arr;
    private int size;
    private int rear;

    public ArrayQueue(int capacity) {
        arr = new double[capacity];
        size = 0;
        rear = -1;
    }

    public void addElementToQueue(double value) {
        arr[++rear] = value;
        size++;
    }

    public double deleteFromElementQueue() {
        double val = arr[0];
        for (int i = 1; i < size; i++) arr[i - 1] = arr[i];
        size--;
        rear--;
        return val;
    }

    public void display() {
        System.out.print("Черга: ");
        for (int i = 0; i < size; i++) System.out.print(arr[i] + " ");
        System.out.println();
    }

    public double[] getElements() {
        double[] copy = new double[size];
        System.arraycopy(arr, 0, copy, 0, size);
        return copy;
    }
}
