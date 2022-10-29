package com.yp.tree.queue;

public class IndexMinPriorityQueueTest {
    public static void main(String[] args) {

        IndexMinPriorityQueue queue = new IndexMinPriorityQueue(10);

        queue.insert(0, "C");
        queue.insert(1, "A");
        queue.insert(2, "F");


        while (!queue.isEmpty()) {
            int i = queue.delMin();
            System.out.println(i + " ");
        }
    }
}

