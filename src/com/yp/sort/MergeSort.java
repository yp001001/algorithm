package com.yp.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int temp[] = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);

        System.out.println("排序后：" + Arrays.toString(arr));
    }


    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //左递归
            mergeSort(arr, left, mid, temp);
            // 右递归
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }



    /**
     * @param arr   排序数组
     * @param left  最左下标
     * @param mid   中间下标
     * @param right 最右下标
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        int r = mid + 1;
        int index = 0;
        while (l <= mid && r <= right) {
            if (arr[l] <= arr[r]) {
                temp[index] = arr[l];
                l += 1;
                index += 1;
            } else {
                temp[index] = arr[r];
                r++;
                index++;
            }
        }

        while (l <= mid) {
            temp[index] = arr[l];
            index++;
            l++;
        }
        while (r <= right) {
            temp[index] = arr[r];
            r++;
            index++;
        }

        // 不是每次都将所有数据保存
        index = 0;
        int ltemp = left;
        while (ltemp <= right) {
            arr[ltemp] = temp[index];
            index++;
            ltemp++;
        }
    }
}
