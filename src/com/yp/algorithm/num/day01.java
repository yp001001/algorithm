package com.yp.algorithm.num;

import java.util.HashSet;
import java.util.Set;

/**
 * 删除排序数组中的重复项
 * <p>
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * nums[]为升序
 */

// 思路，创建一个left，一个right指针，right右移，当与left不一致时，left右移，将right的值赋给left
public class day01 {
    public static void main(String[] args) {
//        int nums[] = {1,1,2,3,4,4,5,7};
//        int nums[] = {1,2};
//        int i = removeDuplicates(nums);
//        System.out.println(i);
//        for(int j=0;j<nums.length;j++){
//            System.out.println(nums[j]);
//        }
        Set<Integer> set = new HashSet<>();
        boolean add = set.add(1);
        System.out.println(add);
        boolean add1 = set.add(1);
        System.out.println(add1);
    }

    public static int removeDuplicates(int[] nums) {
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] != nums[left]) {
                left += 1;
                nums[left] = nums[right];
            }
            right += 1;
        }
        return left+1;
    }
}