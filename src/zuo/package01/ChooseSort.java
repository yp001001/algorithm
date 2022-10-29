package zuo.package01;

import java.util.Arrays;

public class ChooseSort {

    public static void chooseSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 1, 0, 9, 8, 6, 7, 5, 2, -1};
        chooseSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
