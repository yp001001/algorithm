package com.yp2.heap;

public class MinPriorityQueue<T extends Comparable<T>> {

    private T[] items;
    private int N;

    public MinPriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
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
        T t = items[1];
        exch(1, N);
        items[N] = null;
        N--;
        sink(1); // 下浮
        return t;
    }

    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1) {
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

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
            if (less(max, k)) {
                break;
            }
            exch(max, k);
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
