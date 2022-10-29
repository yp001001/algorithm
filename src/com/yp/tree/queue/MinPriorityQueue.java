package com.yp.tree.queue;

/*
小顶堆
 */
public class MinPriorityQueue<T extends Comparable<T>> {
    private T[] items;
    private int N;

    public MinPriorityQueue(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.N = 0;
    }

    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) > 0;
    }

    private void exch(int i, int j) {
        T t = items[i];
        items[i] = items[j];
        items[j] = t;
    }

    public T delMin() {
        exch(1, N);
        T t = items[N];
        N--;
        // 下沉
        sink(1);
        return t;
    }

    public void insert(T t) {
        N++;
        items[N] = t;
        // 上浮
        swim(N);
    }

    // 上浮算法
    private void swim(int k) {
        while (k > 1) {
            // 子元素小于父元素就交换
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k /= 2;
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
            if (!less(max, k)) {
                exch(max, k);
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
