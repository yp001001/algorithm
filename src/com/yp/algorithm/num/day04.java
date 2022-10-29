package com.yp.algorithm.num;

import java.util.Arrays;

/**
 * 给定一个整数数组，判断是否存在重复元素。
 *
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 */
public class day04 {
    public static void main(String[] args) {
        int nums[]= {1,2,3,1};
        boolean b = containsDuplicate(nums);
        System.out.println(b);
    }


    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length-1;i++){
            if(nums[i]==nums[i+1]){
                return true;
            }
        }
        return false;
    }
}
