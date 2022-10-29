package com.yp.tree.heap;

import java.util.Arrays;

public class HeapSortTest {
    public static void main(String[] args) {
        String[] arr = {"S","O","R","T","E","X","A","Bubble","P","L","E"};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
