package zuo.package01.heap;

import java.util.Arrays;

public class Heap<T extends Comparable<T>> {
    private T[] items;
    private int N;

    public Heap(int capacity) {
        items = (T[]) new Comparable[capacity + 1];
        N = 0;
    }

    // 判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }

    private void exch(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    public void insert(T t) {
        items[++N] = t;
        // 上浮
        swim(N);
    }

    // 删除堆中最大元素并返回
    public T delMax() {
        T max = items[1];
        exch(1, N);
        // 下沉
        items[N--] = null;
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (k <= N / 2) {
            int max;
            if (2 * k + 1 <= N) {
                if (less(2 * k, 2 * k + 1)) {
                    max = 2 * k + 1;
                } else {
                    max = 2 * k;
                }
            } else {
                max = 2 * k;
            }
            if (!less(k, max)) break;
            exch(k, max);
            k = max;
        }
    }

    private void swim(int n) {
        while (n > 1) {
            // 如果父节点小于子节点
            if (less(n >> 1, n)) {
                exch(n >> 1, n);
            }
            n >>= 1;
        }
    }


    public static void sort(Comparable[] source) {
        Comparable[] heap = new Comparable[source.length + 1];
        createHeap(source, heap);
        int N = heap.length - 1;
        while (N != 1) {
            exch(heap, 1, N);
            N--;
            sink(heap, 1, N);
        }
        //4.heap中的数据已经有序，拷贝到source中
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    // 根据原数组source，构造出堆heap
    private static void createHeap(Comparable[] source, Comparable[] heap) {
        System.arraycopy(source, 0, heap, 1, source.length);
        for (int i = (heap.length - 1) / 2; i > 0; i--) {
            sink(heap, i, heap.length - 1);
        }
    }

    //判断heap堆中索引i处的元素是否小于索引j处的元素
    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    //交换heap堆中i索引和j索引处的值
    private static void exch(Comparable[] heap, int i, int j) {
        Comparable tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    //在heap堆中，对target处的元素做下沉，范围是0~range
    private static void sink(Comparable[] heap, int target, int range) {
        //没有子节点了
        while (2 * target <= range) {
            int max = 2 * target;
            if (2 * target + 1 <= range) {
                if (less(heap, max, max + 1)) max = max + 1;
            }
            //2.如果当前结点的值小于子结点中的较大值，则交换
            if (less(heap, target, max)) exch(heap, target, max);
            target = max;
        }
    }

    public static void main(String[] args) throws Exception {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        String[] arr2 = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        Arrays.sort(arr2);
        System.out.println(Arrays.toString(arr2));
        Heap.sort(arr);
        System.out.println(Arrays.toString(arr));

    }


}
