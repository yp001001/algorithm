package com.test.num;


import com.yp.sort.PrintUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

//给定两个数组，编写一个函数来计算它们的交集。
public class Intersect {

    public static void main(String[] args) {
        int[] ints = {4, 7, 9, 7, 6, 7};
        int[] ints1 = {5, 0, 0, 6, 1, 6, 2, 2, 4};
        int[] intersect = intersect2(ints, ints1);
        PrintUtil.print(intersect);
    }


    // 方式①：使用hash表  击败78%
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                Integer w = map.get(nums1[i]);
                map.put(nums1[i], ++w);
            } else {
                map.put(nums1[i], 1);
            }
        }
        int index = 0;
        int[] ints = new int[nums1.length > nums2.length ? nums1.length : nums2.length];
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i]) && map.get(nums2[i]) > 0) {
                Integer w = map.get(nums2[i]);
                map.put(nums2[i], --w);
                ints[index++] = nums2[i];
            }
        }
        return Arrays.copyOfRange(ints, 0, index);
    }

    // 方式②：排序后加双指针  击败99.95%
    public static int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n1 = 0;
        int n2 = 0;
        int[] ints = new int[nums1.length > nums2.length ? nums1.length : nums2.length];
        int index = 0;
        while (n1 < nums1.length && n2 < nums2.length) {
            if (nums1[n1] < nums2[n2]) {
                n1++;
            } else if (nums1[n1] > nums2[n2]) {
                n2++;
            } else {
                ints[index++] = nums1[n1];
                n1++;
                n2++;
            }
        }
        return Arrays.copyOfRange(ints, 0, index);
    }
}
