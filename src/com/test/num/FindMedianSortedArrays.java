package com.test.num;

/*
给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 */
public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] num1 = {1, 2};
        int[] num2 = {3, 4}; //1,2,2,3,3,4,4,5,5,6
        double arrays = findMedianSortedArrays(num1, num2);
        System.out.println(arrays);
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = 0;
        int n2 = 0;
        int[] temp = new int[nums1.length + nums2.length];
        int index = 0;
        while (n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] < nums2[n2]) {
                temp[index++] = nums1[n1++];
            } else {
                temp[index++] = nums2[n2++];
            }
        }

        while (n1 < nums1.length) {
            temp[index++] = nums1[n1++];
        }

        while (n2 < nums2.length) {
            temp[index++] = nums2[n2++];
        }

        if (temp.length % 2 == 0) {
            return ((double) temp[temp.length / 2] + temp[temp.length / 2 - 1]) / 2;
        } else {
            return temp[temp.length / 2];
        }
    }

}
