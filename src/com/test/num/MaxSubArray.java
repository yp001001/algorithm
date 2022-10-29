package com.test.num;

/*
给定一个整数数组 nums ，
找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(nums));
    }


    // 方式一：暴力破解
    public static int maxSubArray1(int[] nums) {
        int max = nums[0]; // 保存最大值
        for (int i = 0; i < nums.length; i++) {
            int temp = 0; // 作为中间量
            for (int j = i; j < nums.length; j++) {
                temp += nums[j];
                if (temp > max) max = temp;
            }
        }
        return max;
    }

    // 方式二：贪心算法
    public static int maxSubArray2(int[] nums) {
        int maxSum = 0; // 当前数组和最大值
        int nowSum; // 当前数组值
        Integer oldSum = null; // 之前数组和
        for (int i = 0; i < nums.length; i++) {
            // 当oldSum<0或为null时我们丢弃
            nowSum = oldSum == null || oldSum < 0 ? nums[i] : oldSum + nums[i];
            maxSum = (i != 0 ? Math.max(maxSum, nowSum) : nowSum);
            oldSum = nowSum;
        }
        return maxSum;
    }

    // 方式三：贪心算法2.0
    public static int maxSubArray3(int[] nums) {
        int pre = 0;
        int max = nums[0];
        for (int num : nums) {
            // 如果pre小于0，我们直接舍弃，将num[i]设为当前值
            pre = Math.max(pre + num, num);
            max = Math.max(max, pre);
        }
        return max;
    }
}
