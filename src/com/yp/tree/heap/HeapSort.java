package com.yp.tree.heap;

public class HeapSort {

    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    public static void exch(Comparable[] heap, int i, int j) {
        Comparable temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // 根据原数组source 构建出堆 Heap
    public static void createHeap(Comparable[] source, Comparable[] heap) {
        System.arraycopy(source, 0, heap, 1, source.length);

        for (int i = (heap.length / 2); i > 0; i--) {
            sink(heap, i, heap.length - 1);
        }
    }

    // 对source数组中的数据从小到大排序
    public static void sort(Comparable[] source) {
        Comparable[] heap = new Comparable[source.length + 1];
        createHeap(source, heap);
        int N = heap.length - 1;
        while (N > 1) {
            // 交换顶部和尾部
            exch(heap, 1, N);
            N--;
            sink(heap, 1, N);
        }
        // 将数据拷贝回source
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    // 在heap堆中，对target处的元素做下沉，范围是0-range
    private static void sink(Comparable[] heap, int target, int range) {
        while (target * 2 <= range) {
            // 存储较大元素下标
            int max;
            if (target * 2 + 1 <= range) {
                if (less(heap, target * 2, target * 2 + 1)) {
                    max = target * 2 + 1;
                } else {
                    max = target * 2;
                }
            } else {
                max = 2 * target;
            }
            if (!less(heap, target, max)) {
                break;
            }
            exch(heap, target, max);
            target = max;
        }
    }
}
