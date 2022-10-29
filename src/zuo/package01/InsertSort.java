package zuo.package01;

import java.util.Arrays;

public class InsertSort {

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 插入排序，不稳定（O(n^2)） 最好情况 O(n);
    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 0, 9, 8, 6, 7, 5, 2, -1};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
