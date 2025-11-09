package org.example;

public class StudentTreeLevel3 extends StudentTreeLevel2 {

    public StudentNode balance(StudentNode root) {
        java.util.List<StudentNode> nodes = new java.util.ArrayList<>();
        storeNodes(root, nodes);
        return buildBalanced(nodes, 0, nodes.size() - 1);
    }

    private void storeNodes(StudentNode node, java.util.List<StudentNode> nodes) {
        if (node == null) return;
        storeNodes(node.left, nodes);
        nodes.add(node);
        storeNodes(node.right, nodes);
    }

    private StudentNode buildBalanced(java.util.List<StudentNode> nodes, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        StudentNode node = nodes.get(mid);
        node.left = buildBalanced(nodes, start, mid - 1);
        node.right = buildBalanced(nodes, mid + 1, end);
        return node;
    }

    public void run() {
        StudentTreeLevel3 tree = new StudentTreeLevel3();
        tree.insert(new StudentNode("Іваненко", "Марія", 2, 0));
        tree.insert(new StudentNode("Коваль", "Олег", 3, 5));
        tree.insert(new StudentNode("Петренко", "Анна", 1, 2));
        tree.insert(new StudentNode("Сидоренко", "Дмитро", 4, 4));
        tree.insert(new StudentNode("Мельник", "Ігор", 2, 3));

        System.out.println("Дерево до балансування:");
        tree.display();

        StudentNode balanced = tree.balance(tree.getRoot());
        System.out.println("\nДерево після балансування:");
        tree.inorder(balanced);
    }
}
