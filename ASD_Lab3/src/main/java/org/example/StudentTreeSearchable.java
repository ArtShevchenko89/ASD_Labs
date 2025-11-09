package org.example;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

public class StudentTreeSearchable extends StudentTreeBasic {

    public StudentNode searchParallel(long key) {
        if (getRoot() == null) return null;
        ForkJoinPool pool = ForkJoinPool.commonPool();
        return pool.invoke(new SearchTask(getRoot(), key));
    }

    public void searchAndDisplay(long key) {
        StudentNode found = searchParallel(key);
        if (found != null) {
            System.out.println("Знайдено студента (паралельний пошук):");
            found.display();
        } else {
            System.out.println("Студента з таким номером не знайдено.");
        }
    }

    private static class SearchTask extends RecursiveTask<StudentNode> {
        private final StudentNode node;
        private final long key;

        public SearchTask(StudentNode node, long key) {
            this.node = node;
            this.key = key;
        }

        @Override
        protected StudentNode compute() {
            if (node == null) return null;
            if (node.studentId == key) return node;

            SearchTask leftTask = new SearchTask(node.left, key);
            SearchTask rightTask = new SearchTask(node.right, key);

            leftTask.fork();
            StudentNode rightResult = rightTask.compute();
            StudentNode leftResult = leftTask.join();

            if (node.studentId == key) return node;
            if (leftResult != null) return leftResult;
            if (rightResult != null) return rightResult;

            return null;
        }
    }
}
