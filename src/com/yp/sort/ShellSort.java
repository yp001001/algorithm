package com.yp.sort;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 9, 5, -1, 4, 10, 20, 17};
        shellSort(array);
        PrintUtil.print(array);
//        int[] arr = new int[80000];
//        for (int i = 0; i < 80000; i++) {
//            arr[i] = (int) (Math.random() * 8000000);
//        }
//        System.out.println("排序前");
//        long start = System.currentTimeMillis();
//        shellSort(arr);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }


    /**
     * 希尔排序
     *
     * @param array
     */
    public static void sort(int[] array) {
        // 步长
        for (int grap = array.length / 2; grap > 0; grap /= 2) {
            // 步长交换次数
            for (int i = grap; i < array.length; i++) {
                for (int j = i - grap; j >= 0; j -= grap) {
                    if (array[j] > array[j + grap]) {
                        int temp = array[j];
                        array[j] = array[j + grap];
                        array[j + grap] = temp;
                    }
                }
            }
        }
    }

    public static void shellSort(int[] arr) {
        // 增量gap，并逐步缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，组个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        // 移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出循环时，插入
                    arr[j] = temp;
                }
            }
        }
    }
}
