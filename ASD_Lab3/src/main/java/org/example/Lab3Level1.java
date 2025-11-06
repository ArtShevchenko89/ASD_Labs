package org.example;

// Клас вузла дерева
class StudentNode {
    String lastName;
    String firstName;
    int course;
    long studentId; // ключ
    double scholarship;

    StudentNode left;
    StudentNode right;

    public StudentNode(String lastName, String firstName, int course, long studentId, double scholarship) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.course = course;
        this.studentId = studentId;
        this.scholarship = scholarship;
        this.left = null;
        this.right = null;
    }

    public void display() {
        System.out.printf("%-10s %-10s %-6d %-10d %-8.2f\n",
                lastName, firstName, course, studentId, scholarship);
    }
}

// Клас бінарного дерева
class StudentTree {
    private StudentNode root;

    public StudentTree() {
        root = null;
    }

    // Додавання вузла
    public boolean insert(StudentNode node) {
        if (root == null) {
            root = node;
            return true;
        }
        return insertRecursive(root, node);
    }

    private boolean insertRecursive(StudentNode current, StudentNode node) {
        if (node.studentId == current.studentId) {
            return false; // ключ унікальний
        }
        if (node.studentId < current.studentId) {
            if (current.left == null) {
                current.left = node;
                return true;
            } else {
                return insertRecursive(current.left, node);
            }
        } else {
            if (current.right == null) {
                current.right = node;
                return true;
            } else {
                return insertRecursive(current.right, node);
            }
        }
    }

    // Виведення дерева (pre-order)
    public void display() {
        System.out.printf("%-10s %-10s %-6s %-10s %-8s\n", "LastName", "FirstName", "Course", "ID", "Scholarship");
        displayRecursive(root);
    }

    private void displayRecursive(StudentNode node) {
        if (node == null) return;
        node.display();
        displayRecursive(node.left);
        displayRecursive(node.right);
    }
}

// Тест програми
public class Lab3Level1 {
    public static void main(String[] args) {
        StudentTree tree = new StudentTree();

        // Додаємо студентів з конкретними номерами студентських квитків
        tree.insert(new StudentNode("Shevchenko", "Artem", 3, 7560451, 1200));
        tree.insert(new StudentNode("Ivanov", "Sergey", 3, 7560452, 1100));
        tree.insert(new StudentNode("Petrenko", "Oleg", 1, 7560453, 1000));
        tree.insert(new StudentNode("Koval", "Anna", 2, 7560454, 1300));
        tree.insert(new StudentNode("Bondar", "Nina", 4, 7560455, 1500));

        tree.display();
    }
}
