package com.yp.sort;

import java.util.Arrays;

// 联系
public class Test {

    public static void main(String[] args) {
        int[] array = {3, 5, 6, 1, 9, 0, 8, 7, 2, 4, -1};
        quickSort(array, 0, array.length - 1);
        PrintUtil.print(array);
    }


    //冒泡排序
    public static void bubbleSort(int[] array) {
        // 优化
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = false;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (flag) {
                break;
            }
        }
    }


    // 插入排序
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = i;
            while (temp > 0 && array[temp] < array[temp - 1]) {
                // 交换
                int result = array[temp];
                array[temp] = array[temp - 1];
                array[temp - 1] = result;
                temp--;
            }
        }
    }


    // 选择排序
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int temp = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[temp] > array[j]) {
                    temp = j;
                }
            }
            if (temp != i) {
                int result = array[i];
                array[i] = array[temp];
                array[temp] = result;
            }
        }
    }


    // 希尔排序（位移版） （难点!!!）
    public static void shellSort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j = i;
                int temp = array[i];
                // 因为已经排好序
                if (array[j] < array[j - gap]) {
                    // 注意：是 j 和 temp （i不能改变）
                    while (j - gap >= 0 && temp < array[j - gap]) {
                        array[j] = array[j - gap];
                        j -= gap;
                    }
                    // 退出循环时插入
                    array[j] = temp;
                }
            }
        }
    }


    // 快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int l = left;
            int r = right;
            // 作为基准数
            int stand = arr[left];

            while (l < r) {

                // 以左边为基准数则必须先从右边开始
                while (l < r && arr[r] >= stand) {
                    r--;
                }
                arr[l] = arr[r];

                while (l < r && arr[l] <= stand) {
                    l++;
                }
                arr[r] = arr[l];
            }
            arr[l] = stand;

            // 开始递归
            quickSort(arr, left, l);
            quickSort(arr, l + 1, right);
        }
    }

//    // 快速排序
//    public void quickSort2(int[] arr, int left, int right) {
//        if (left < right) {
//            int temp = arr[left]; // 作为基准数
//            int l = left;
//            int r = right;
//            while (l < r && arr[r] > temp) {
//                r--;
//            }
//
//        }
//    }

}
