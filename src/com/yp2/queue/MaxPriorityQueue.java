package com.yp2.queue;

public class MaxPriorityQueue<T extends Comparable<T>> {


    private T[] items;
    private int N;

    public MaxPriorityQueue(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.N = 0;
    }


    public int size() {
        return -1;
    }

    public boolean isEmpty() {
        return false;
    }


    // 判断堆中索引处i的元素是否小于索引j处的元素
    public boolean less(int i, int j) {
        return false;
    }

    // 交换堆中i索引和j索引处的值
    public void exch(int i, int j) {

    }

    // 往堆中插入一个元素
    public void insert(T t) {

    }
}
