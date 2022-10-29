package com.yp.tree.queue;

/*
最大优先队列
 */
public class MaxPriorityQueue<T extends Comparable<T>> {
    private T[] items;// 存储元素数组
    private int N;    // 记录堆中元素个数

    public MaxPriorityQueue(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void exch(int i, int j) {
        T t = items[i];
        items[i] = items[j];
        items[j] = t;
    }

    public T delMax() {
        T item = items[1];
        exch(1, N);
        N--;
        sink(1);
        return item;
    }

    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    // 上浮算法
    private void swim(int k) {
        while (k > 1) {
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

    // 下沉算法
    private void sink(int k) {
        while (k * 2 <= N) {
            int max;
            if (k * 2 + 1 <= N) {
                if (less(k * 2, k * 2 + 1)) {
                    max = k * 2 + 1;
                } else {
                    max = k * 2;
                }
            } else {
                max = k * 2;
            }
            if (less(k, max)) {
                exch(k, max);
            }
            k = max;
        }
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }
}
