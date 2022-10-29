package com.yp.tree.queue;

//A C B
//1 3 2  // 保存的是小顶堆后的顺序，存储内容为原数组中的索引
/*
索引最小优先队列设计
 */
public class IndexMinPriorityQueue<T extends Comparable<T>> {

    private T[] items;
    private int[] pq; // 保存每个元素在items数组中的索引，pq数组需要堆有序
    private int[] qp; // 保存qp的逆序，pq的值作为索引，qp的索引作为值
    private int N;    // 记录堆中元素的个数

    public IndexMinPriorityQueue(int capacity) {
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

    private void exch(int i, int j) {
        // 将pq中的数组元素交换
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        // qp保存的是pq的元素，也需要调整
        qp[pq[j]] = j;
        qp[pq[i]] = i;
    }

    public int delMin() {
        // 获取最小元素关联的索引
        int minIndex = pq[1];

        // 交换pq中索引1处和最大索引处的元素
        exch(1, N);
        // 删除qp中对应的内容
        qp[pq[N]] = -1;
        // 删除pq最大索引处的内容
        pq[N] = -1;
        // 删除item中对应的内容
        items[minIndex] = null;
        // 元素个数-1
        N--;
        // 下沉调整
        sink(1);
        return minIndex;
    }

    // 删除索引i关联的元素
    public void delete(int i) {
        // 找到i在pq中的索引
        int k = pq[i];
        // 交换pq中索引k处的值和索引N处的值
        exch(i, N);
        qp[pq[N]] = -1;
        pq[N] = -1;
        items[k] = null;
        N--;
        // 因为放在中间，所以我们应该先上浮再下沉
        swim(i);
        sink(1);

    }

    // 从队列中插入一个元素，并关联索引i
    public void insert(int i, T t) {
        if (contains(i)) {
            return;
        }
        N++;
        items[i] = t;
        pq[N] = i;
        qp[i] = N;
        swim(N);
    }

    private void swim(int k) {
        while (k > 1) {
            if (less(k, k / 2)) {
                exch(k, k / 2);
            }
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            // 找到子节点中的较小值
            int min;
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    min = 2 * k;
                } else {
                    min = 2 * k + 1;
                }
            }else{
                min = 2*k;
            }
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
        sink(k);
        swim(k);
    }

    public int minIndex() {
        return -1;
    }

}
