package com.yp2.heap.test;

import com.yp2.heap.Heap;

public class HeapTest {

    public static void main(String[] args) {
        Heap<String> heap = new Heap<>(10);
        heap.insert("A");
        heap.insert("B");
        heap.insert("D");
        heap.insert("C");
        heap.insert("P");
        heap.insert("R");
        heap.insert("G");
        heap.insert("V");

        String result = null;
        while ((result = heap.delMax()) != null) {
            System.out.println(result);
        }
    }
}
