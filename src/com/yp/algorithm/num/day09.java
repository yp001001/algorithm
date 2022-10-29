package com.yp.algorithm.num;

import java.util.Arrays;

/**
 * 移动0
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class day09 {

    public static void main(String[] args) {
        int[] nums = {1,0,2,1,0,10,0,0,2};
        moveZeroes(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }


    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0)
            return;
        int index = 0;
        //一次遍历，把非零的都往前挪
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                nums[index++] = nums[i];
        }
        //后面的都是0,
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
