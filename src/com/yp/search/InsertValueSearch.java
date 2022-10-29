package com.yp.search;

import java.util.List;

/*
 插值查找算法 （用于数值比较均匀的情况）
 int mid = left + (right-left) * (findVal-arr[left]) / (arr[right]-arr[left])
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 10, 89, 1000, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 1000);
        System.out.println(index);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {

        if (left > right || findVal < arr[0] || findVal > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] > findVal) {
            // 左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else if (arr[mid + 1] < findVal) {
            // 右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else {
            return mid;
        }
    }
}
