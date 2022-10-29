package com.yp.sort;

/**
 * 选择排序
 * 思想：
 * 第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，
 * 第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换
 * ……
 * 得到一个按排序码从小到大排序的有序序列
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] array = {1, 3, 2, 9, 5, -1,4, 10, 20, 17};
        sort(array);
        PrintUtil.print(array);
    }

    /**
     * 选择排序 (不稳定 时间复杂度为（O(n^2)）)
     *
     * @param array
     */
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 假设第一个数为最小数
            int min = array[i];
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[index] > array[j]) {
                    index = j;
                }
            }
            array[i] = array[index];
            array[index] = min;
        }
    }
}
