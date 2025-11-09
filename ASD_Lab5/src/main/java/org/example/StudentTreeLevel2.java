package org.example;

public class StudentTreeLevel2 {
    private StudentNode root;

    public void insert(StudentNode node) { root = insertRecursive(root, node); }

    private StudentNode insertRecursive(StudentNode current, StudentNode node) {
        if (current == null) return node;
        if (node.debts < current.debts) current.left = insertRecursive(current.left, node);
        else                             current.right = insertRecursive(current.right, node);
        return current;
    }

    public StudentNode searchByDebts(int key) { return searchRecursive(root, key); }

    private StudentNode searchRecursive(StudentNode node, int key) {
        if (node == null || node.debts == key) return node;
        return key < node.debts ? searchRecursive(node.left, key)
                : searchRecursive(node.right, key);
    }

    public void inorder(StudentNode node) {
        if (node != null) { inorder(node.left); node.display(); inorder(node.right); }
    }

    public void display() {
        System.out.println("\n=== Вміст дерева (ключ: debts) ===");
        inorder(root);
    }

    public StudentNode getRoot() { return root; }

    // --- додано ---
    public void run() {
        insert(new StudentNode("Іваненко","Марія",2,0));
        insert(new StudentNode("Коваль","Олег",3,5));
        insert(new StudentNode("Петренко","Анна",1,2));
        insert(new StudentNode("Сидоренко","Дмитро",4,4));
        insert(new StudentNode("Мельник","Ігор",2,3));

        display();

        int key = 3; // шукаємо студента(ів) з 3 боргами
        System.out.println("\nПошук вузла з debts = " + key + ":");
        StudentNode f = searchByDebts(key);
        if (f != null) f.display();
        else System.out.println("Не знайдено.");
    }
}
