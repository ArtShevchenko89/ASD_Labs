package org.example;

public class StudentTreeBasic {
    private StudentNode root;

    public void insert(StudentNode node) {
        if (root == null) {
            root = node;
        } else {
            insertRecursive(root, node);
        }
    }

    private void insertRecursive(StudentNode current, StudentNode node) {
        if (node.studentId < current.studentId) {
            if (current.left == null) current.left = node;
            else insertRecursive(current.left, node);
        } else {
            if (current.right == null) current.right = node;
            else insertRecursive(current.right, node);
        }
    }

    private void inorder(StudentNode node) {
        if (node != null) {
            inorder(node.left);
            node.display();
            inorder(node.right);
        }
    }

    public void display() {
        System.out.printf("%-10s %-10s %-6s %-10s %-8s\n",
                "Прізвище", "Ім’я", "Курс", "Квиток", "Стипендія");
        inorder(root);
    }

    public StudentNode getRoot() {
        return root;
    }
}
