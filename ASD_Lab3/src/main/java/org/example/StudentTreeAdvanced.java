package org.example;

public class StudentTreeAdvanced extends StudentTreeSearchable {
    public void findNoScholarshipFourthCourse(StudentNode node) {
        if (node == null) return;

        findNoScholarshipFourthCourse(node.left);

        if (node.course == 4 && node.scholarship == 0) {
            node.display();
        }

        findNoScholarshipFourthCourse(node.right);
    }
}
