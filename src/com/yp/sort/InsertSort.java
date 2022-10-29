package com.yp.sort;

import com.yp.stack.Test;

/**
 * 插入排序法 (使用场景，大部分已经排序好的数据，数据量小)
 * 思想：
 * 把n个待排序的元素看成一个有序表和一个无序表，开始时有序表中只包含一个元素
 * 无序表中包含n-1个元素，排序过程中每次从无序表中取出第一个元素，
 * 把他的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] array = {1, 3, 2, 9, 5, -1, 4, 10, 20, 17};
//        sort(array);
//        PrintUtil.print(array);
        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        System.out.println("排序前");
        long start = System.currentTimeMillis();
        sort(arr);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void sort(int[] array) {
        int index = 0; // 有序表的最后一个元素
        for (int i = 1; i < array.length; i++) {
            while (index >= 0 && array[index + 1] < array[index]) {
                int temp = array[index + 1];
                array[index + 1] = array[index];
                array[index] = temp;
                index--;
            }

            index = i;
        }
    }
}
