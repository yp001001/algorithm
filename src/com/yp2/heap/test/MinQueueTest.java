package com.yp2.heap.test;

import com.yp2.heap.MinPriorityQueue;

public class MinQueueTest {
    public static void main(String[] args) throws Exception {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        MinPriorityQueue<String> maxpq = new MinPriorityQueue<>(20);
        for (String s : arr) {
            maxpq.insert(s);
        }
//         A,E,E,L,M,O,P,R,S,T,X,
//         X,T,S,R,P,O,M,L,E,E,A,
        System.out.println(maxpq.size());
        String del;
        while (!maxpq.isEmpty()) {
            del = maxpq.delMin();
            System.out.print(del + ",");
        }
    }
}
