package com.yp.sort;


/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {1, 3, 2, 9, 5, -1, 4, 10, 20, 17};
        sort_2(array);
        PrintUtil.print(array);
    }


    public static void sort_2(int[] nums) {
        if (nums == null || nums.length == 1) return;
        boolean flag;
        for (int i = 0; i < nums.length - 1; i++) {
            flag = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    flag = false;
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
            if(flag){
                return;
            }
        }
    }
    final void test(){}


    /**
     * 冒泡排序 时间复杂度（O(n^2)） 稳定
     * 思路：
     * 1. 两个指针，相互比较，如果前者大于后者，相互交换，然后继续向后比较
     * 2. 头层循环只需要循环 array.length - 1 次
     * 3. 次层循环从0开始，循环到 array.length - i - 1 次
     * <p>
     * 优化 (当循环一遍没有发生交换时，说明此时数组已经有序，可以不需要进行排序)
     *
     * @param array
     */
    public static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    flag = false;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            if (flag) {
                return;
            }
        }
    }
}
