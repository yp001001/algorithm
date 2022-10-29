package zuo.package01;

import java.util.Arrays;

public class BubbleSort {


    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 0, 9, 8, 6, 7, 5, 2, -1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
