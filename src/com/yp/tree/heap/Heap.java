package com.yp.tree.heap;

public class Heap<T extends Comparable<T>> {
    // 存储堆中的元素
    private T[] items;
    // 记录堆中元素的个数
    private int N;

    public Heap(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
    }

    // 判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    // 交换堆中i索引和j索引处的值
    private void exch(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    // 往堆中插入一个元素
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }

    // 使用上浮算法，使索引k处的元素在堆中处于一个正确的位置
    private void swim(int k) {
        while (k > 1) {
            if (less(k / 2, k)) {
                exch(k / 2, k);
            }
            k = k / 2;
        }
    }

    // 删除堆中最大的元素，并返回这个最大元素
    public T delMax() {
        T t = items[1];
        exch(1, N);
        items[N] = null;
        N--;
        sink(1);
        return t;
    }

    // 使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    // 1. 注意有可能没有右子节点
    // 2. 左右子节点更大元素与父节点比较，大者在上
    private void sink(int k) {
        while (k * 2 <= N) {
            if (k * 2 + 1 <= N) {
                boolean less = less(k * 2, k * 2 + 1);
                if (less) {
                    // 当子节点较大值小于父节点
                    if (less(k * 2 + 1, k)) {
                        return;
                    } else {
                        exch(k * 2 + 1, k);
                        k = k * 2 + 1;
                    }
                } else {
                    if (less(k * 2, k)) {
                        return;
                    } else {
                        exch(k * 2, k);
                        k = k * 2;
                    }
                }
            } else {
                if (!less(k * 2, k)) {
                    exch(k * 2, k);
                }
                return;
            }
        }
    }
}
