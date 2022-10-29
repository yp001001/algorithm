package com.yp2.heap;

/**
 * 每个节点都大于子节点
 *
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {
    private T[] items;
    private int N;

    public Heap(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }


    public T delMax() {
        if (N == 0) {
            return null;
        }
        T t = items[1];
        exch(1, N);
        items[N] = null;
        N--;
        sink(1);
        return t;
    }

    public void insert(T t) {
        this.items[++N] = t;
        // 上浮
        swim(N);
    }

    /**
     * 上浮算法
     *
     * @param k
     */
    private void swim(int k) {
        while (k > 1) {  // k/2 > = 1;
            if (less(k / 2, k)) {  // 判断父节点是否小于子节点
                exch(k, k / 2);
            }
            k = k / 2;
        }
    }

    /**
     * 下沉算法
     *
     * @param k
     */
    private void sink(int k) {
        while (k <= N / 2) {  // 至少要有一个子节点才进行判断
            // 没有判断 k*2+1，会超出边界
            int max;  // 得到子节点的较大值的下标
            if (k * 2 + 1 <= N) {
                max = less(k * 2, k * 2 + 1) ? k * 2 + 1 : k * 2;
            } else {
                max = k * 2;
            }
            if (!less(k, max)) {
                break;
            }
            exch(k, max);
            k = max;
        }
    }


    /**
     * 判断堆中索引处i的元素是否小于索引j处的元素
     *
     * @param i
     * @param j
     * @return
     */
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    /**
     * 交换堆中i索引和j索引处的值
     *
     * @param i
     * @param j
     */
    private void exch(int i, int j) {
        T t = items[i];
        items[i] = items[j];
        items[j] = t;
    }
}
