package com.yp.tree.queue;

/*
索引最大优先级队列设计
 */
public class IndexMaxPriorityQueue<T extends Comparable<T>> {

    private T[] items;
    private int[] pq; // 保存每个元素在items数组中的索引，pq数组需要堆有序
    private int[] qp; // 保存qp的逆序，pq的值作为索引，qp的索引作为值
    private int N;    // 记录堆中元素的个数

    public IndexMaxPriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        pq = new int[capacity + 1];
        qp = new int[capacity + 1];
        N = 0;

        // 默认情况下，队列中没有存储任何数据，让qp中所有元素全部为-1
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    // 只是用来作为大顶堆时的方法，我们不应该修改items中的数据
    private void exch(int i, int j) {
        int k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
        // 交换qp
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public int delMax() {
        // 获取最大元素对应的索引
        int maxIndex = pq[1];
        exch(1, N);
        qp[pq[N]] = -1;
        pq[N] = -1;
        items[maxIndex] = null;
        N--;
        sink(1);
        return maxIndex;
    }

    // 删除索引i关联的元素
    public void delete(int i) {
        int index = pq[i];
        exch(index, N);
        qp[pq[N]] = -1;
        pq[N] = -1;
        items[index] = null;
        N--;
        sink(i);
        swim(N);
    }

    // 从队列中插入一个元素，并关联索引i
    public void insert(int i, T t) {
        if (contains(i)) {
            return;
        }
        items[i] = t;
        pq[++N] = i;
        qp[i] = pq[N];
        swim(N);
    }

    private void swim(int k) {
        while (k > 1) {
            if (less(k / 2, k)) {
                exch(k, k / 2);
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
            if (!less(k, max)) {
                break;
            }
            exch(k, max);
            k = max;
        }
    }

    private int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    // 把与索引i关联的元素修改为t
    public void changeItem(int i, T t) {
        items[i] = t;
        int k = qp[i];
        swim(k);
        sink(1);
    }

    public int MaxIndex() {
        return -1;
    }

}
