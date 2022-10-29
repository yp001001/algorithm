package com.yp2.heap;

public class IndexMinPriorityQueue<T extends Comparable<T>> {
    //存储堆中的元素
    private T[] items;
    //保存每个元素在items数组中的索引，pq数组需要堆有序
    private int[] pq;
    //保存qp的逆序，pq的值作为索引，pq的索引作为值
    private int[] qp;
    //记录堆中元素的个数
    private int N;

    public IndexMinPriorityQueue(int capacity) {
        this.items = (T[]) new Comparable[capacity + 1];
        this.pq = new int[capacity + 1];
        this.qp = new int[capacity + 1];
        for (int i = 0; i < qp.length; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return N == 0;
    }

    private boolean less(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) < 0;
    }

    private void exch(int i, int j) {
        // 交换pq数组中的值
        int x = pq[i];
        pq[i] = pq[j];
        pq[j] = x;
        // 交换qp数组中的值
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public void delMin() {
        pq[1] = pq[N];

    }

    public void insert(int i, T t) {
        if (contains(i)) {
            throw new RuntimeException("该索引已经存在");
        }
        N++;
        items[i] = t;
        pq[N] = i;
        qp[pq[N]] = N;
        // 上浮
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
        //如果当前结点已经没有子结点了，则结束下沉
        while (2 * k <= N) {
            //找出子结点中的较小值
            int min = 2 * k;
            if (2 * k + 1 <= N && less(2 * k + 1, 2 * k)) {
                min = 2 * k + 1;
            }
            //如果当前结点的值比子结点中的较小值小，则结束下沉
            if (less(k, min)) {
                break;
            }
            exch(k, min);
            k = min;
        }
    }

    private int size() {
        return N;
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void changeItem(int i, T t) {
        // 修改items数组中索引i处的值为t
        items[i] = t;
        // 得到pq保存该元素的下标
        int k = qp[i];
        // 下沉，上浮，让堆有序
        sink(k);
        swim(k);
    }

    public int minIndex() {
        return pq[1];
    }

    /**
     * 删除指定索引的元素
     *
     * @param i
     */
    public void delete(int i) {
        // 找出i在pq中的索引
        int k = qp[i];
        // 把pq中索引k处的值和N处的值进行交换
        exch(k, N);
        // 删除qp中索引pq[N]处的值
        qp[pq[N]] = -1;
        pq[N] = -1;
        // 删除items索引处i的值
        items[k] = null;
        // 下沉，上浮，让堆有序
        sink(k);
        swim(k);
    }


    public int delMin(int i) {
        int minIndex = pq[1];
        exch(1, N);
        // 删除qp对应的内容
        qp[pq[N]] = -1;
        pq[N] = -1;
        items[pq[N]] = null;
        sink(1);
        return minIndex;
    }

}
