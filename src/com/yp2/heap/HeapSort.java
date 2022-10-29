package com.yp2.heap;


public class HeapSort {

    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    private static void exch(Comparable[] heap, int i, int j) {
        Comparable c = heap[i];
        heap[i] = heap[j];
        heap[j] = c;
    }

    private static void createHeap(Comparable[] source, Comparable[] heap) {
        System.arraycopy(source, 0, heap, 1, source.length); // 复制
        for (int i = (source.length - 1) / 2; i >= 1; i--) {
            sink(heap, i, heap.length - 1);
        }
    }

    public static void sort(Comparable[] source) {
        Comparable[] heap = new Comparable[source.length + 1];
        createHeap(source, heap);
        int N = heap.length - 1;
        while (N > 1) {
            exch(heap, 1, N); // 交换元素
            N--; // 需要先--，否则sink会算到N
            sink(heap, 1, N);
        }
        System.arraycopy(heap, 1, source, 0, source.length);
    }

    private static void sink(Comparable[] heap, int target, int range) {
        while (target * 2 <= range) {
            int max;
            // 得到子节点较大元素的下标
            if (target * 2 + 1 <= range) {
                if (less(heap, target * 2, target * 2 + 1)) {
                    max = target * 2 + 1;
                } else {
                    max = target * 2;
                }
            } else {
                max = target * 2;
            }
            if (less(heap, max, target)) {
                break;
            }
            exch(heap, max, target);
            target = max;
        }
    }
}
