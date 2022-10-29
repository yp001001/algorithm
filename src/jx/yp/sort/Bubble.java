package jx.yp.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class Bubble {

    public static void main(String[] args) {
        int[] arr = {2, 4, 5, 6, 1, 10, 9, 8, 7, 20, 0};
        BubbleSort2(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    public static void BubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 优化
     *
     * @param arr
     */
    public static void BubbleSort2(int[] arr) {
        int temp;
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = false;
                }
            }
            if (!flag) {
                flag = true;
            }
        }
    }
}
