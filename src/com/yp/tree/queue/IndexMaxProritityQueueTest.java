package com.yp.tree.queue;

public class IndexMaxProritityQueueTest {

    public static void main(String[] args) {
        IndexMaxPriorityQueue queue = new IndexMaxPriorityQueue(10);

        queue.insert(0, "C");
        queue.insert(1, "A");
        queue.insert(2, "F");
        queue.insert(3, "G");
        queue.insert(4, "B");


        while (!queue.isEmpty()) {
            int i = queue.delMax();
            System.out.println(i + " ");
        }
    }
}
