package com.test.num;

import java.util.Arrays;

/*
给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 */
public class Merge {


    // 也可以从后往前遍历，就不需要临时数组
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int l = 0;
        int r = 0;
        int index = 0;
        int[] sum = new int[m + n];
        while (l < m && r < n) {
            if (nums1[l] < nums2[r]) {
                sum[index++] = nums1[l++];
            } else {
                sum[index++] = nums2[r++];
            }
        }
        while (l < m) {
            sum[index++] = nums1[l++];
        }
        while (r < n) {
            sum[index++] = nums2[r++];
        }
        for (int i = 0; i < sum.length; i++) {
            nums1[i] = sum[i];
        }
    }
}
