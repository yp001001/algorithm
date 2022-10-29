package com.yp.sort;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 快速排序：
 * 思路：
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小
 * 然后再按此方法对比这两部分数据分别进行快速排序
 * 整个排序过程可以递归进行，依次达到有序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 9, 5, -1, 4, 10, 18, 17};
        quickSort_2(array, 0, array.length - 1);
        PrintUtil.print(array);
    }


    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {

            // 把数组中的第0个数字作为基准数
            int stard = arr[left];
            // 记录需要排序的下标
            int low = left;
            int high = right;
            // 循环找比标准数大的数和比标准数小的数
            while (low < high) {
                // 右边的数字比标准数大
                while (low < high && stard <= arr[high]) {
                    high--;
                }

                // 使用右边的数字替换左边的数字
                arr[low] = arr[high];

                // 左边的数字比标准数小
                while (low < high && stard >= arr[high]) {
                    low++;
                }
                arr[high] = arr[low];
            }
            // 把标准数赋给低所在的位置的元素
            arr[low] = stard;

            // 处理所有小的数字
            quickSort(arr, left, low);
            // 处理所有大的数字
            quickSort(arr, low + 1, right);
        }
    }


    public static void quickSort_2(int[] nums, int left, int right) {
        if (left < right) {
            int stard = nums[left];
            int low = left, high = right;
            while (low < high) {
                while (low < high && nums[high] >= stard) {
                    high--;
                }
                nums[low] = nums[high];
                while (low < high && nums[low] <= stard) {
                    low++;
                }
                nums[high] = nums[low];
            }
            nums[low] = stard;
            quickSort_2(nums, left, low);
            quickSort_2(nums, low + 1, right);
        }
    }
}
