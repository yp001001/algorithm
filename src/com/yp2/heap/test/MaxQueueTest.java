package com.yp2.heap.test;

import com.yp2.heap.MaxPriorityQueue;

public class MaxQueueTest {
    public static void main(String[] args) throws Exception {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        MaxPriorityQueue<String> maxpq = new MaxPriorityQueue<>(20);
        for (String s : arr) {
            maxpq.insert(s);
        }
        System.out.println(maxpq.size());
        String del;
        while (!maxpq.isEmpty()) {
            del = maxpq.delMax();
            System.out.print(del + ",");
        }
    }
}
