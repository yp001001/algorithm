package com.test.num;

import java.util.*;

/*
给定一个整数数组 nums 和一个整数目标值 target，
请你在该数组中找出 和为目标值 target的那 两个 整数，并返回它们的数组下标。
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 2, 7, 11, 15, -6};
        int[] integers = twoSum(arr, 9);
        for (int integer : integers) {
            System.out.println(integer);
        }
    }


    // ①：暴力破解
    public static int[] twoSum(int[] nums, int target) {

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    set.add(i);
                    set.add(j);
                }
            }
        }

        Iterator<Integer> iterator = set.iterator();
        int[] ints = new int[set.size()];
        int j = 0;
        while (iterator.hasNext()) {
            ints[j] = iterator.next();
            j++;
        }
        return ints;
    }

    // ②：Hash表
    public static int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        // map Key保存nums[i]补位的值，value保存下标
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
            }
            // 保存补位信息
            map.put(target - nums[i], i);
        }
        return result;
    }
}
