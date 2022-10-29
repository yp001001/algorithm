package com.yp.algorithm.num;

//给定两个数组，编写一个函数来计算它们的交集。

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * <p>
 * <p>
 * 先对两个数组进行排序，然后使用两个指针，分别指向两个数组开始的位置。
 * <p>
 * 如果两个指针指向的值相同，说明这个值是他们的交集，就把这个值加入到集合list中，然后两个指针在分别往后移一步。
 * 如果两个指针指向的值不同，那么指向的值相对小的往后移一步，相对大的先不动，然后再比较
 * 一直重复上面的操作，直到其中一个指针不能再移动为止，最后再把集合list转化为数组即可
 */
public class day07 {

    public static void main(String[] args) {
        int nums1[] = {1, 2, 2, 1};
        int nums2[] = {2, 2};
        int[] intersect = intersect(nums1, nums2);
        for (int i : intersect) {
            System.out.println(i);
        }
    }


    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        ArrayList<Integer> list = new ArrayList();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                // 数值小的往后走
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] temp = new int[list.size()];
        for (int m = 0; m < list.size(); m++) {
            temp[m] = list.get(m);
        }
        return temp;
    }
}
