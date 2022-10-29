package com.yp.algorithm.num;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出
 * 和为目标值 targer的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 */
public class day10 {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;
        int[] ints = twoSum2(nums, target);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    //    暴力破解
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    //    使用HashMap
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (m.get(target - nums[i]) != null) {
                return new int[]{m.get(target - nums[i]), i};
            }
            m.put(nums[i], i);
        }
        return new int[]{0, 0};
    }

}
