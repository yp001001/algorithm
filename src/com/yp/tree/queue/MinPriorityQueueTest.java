package com.yp.tree.queue;

public class MinPriorityQueueTest {
    public static void main(String[] args) {
        MinPriorityQueue queue = new MinPriorityQueue(10);
        queue.insert("F");
        queue.insert("B");
        queue.insert("A");
        queue.insert("C");
        queue.insert("D");
        queue.insert("E");
        queue.insert("G");

        while (!queue.isEmpty()) {
            System.out.println(queue.delMin());
        }
    }
}
