package com.yp.sort;

import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) {
        String s1 = "haha";
        String s2 = "haha";
        String s3 = "haha";
        System.out.println(s1 == s2);
        System.out.println(s2 == s3);
    }

    public int[] sortArray(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];
        mergeSort(arr, 0, n - 1, temp);
        return arr;
    }

    public void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    public void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left, r = mid + 1;
        int index = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[index++] = arr[l++];
            } else {
                temp[index++] = arr[r++];
            }
        }
        while (l <= mid) temp[index++] = arr[l++];
        while (r <= right) temp[index++] = arr[r++];
        index = 0;
        for (int i = left; i <= right; i++) {
            arr[i] = temp[index++];
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
