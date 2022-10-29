package com.yp.algorithm.num;

import java.util.Arrays;

// 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 public class day05 {

    public static void main(String[] args) {
        int nums[] = {1,1,2,3,2,4,4};
        int i = singleNumber2(nums);
        System.out.println(i);
    }


    public static int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[nums.length - 1];
    }

 // 使用异或操作符，相同为 0 不同为 1
    public static int singleNumber2(int[] nums) {
        int reduce = 0;
        for (int num : nums) {
            reduce =  reduce ^ num;
        }
        return reduce;
    }
}
