package com.test.num;

import com.yp.sort.PrintUtil;

/*
给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。
 */
public class Addone {

    public static void main(String[] args) {
        int[] ints = plusOne(new int[]{9});
        PrintUtil.print(ints);
    }

    public static int[] plusOne(int[] digits) {
        int x = digits.length - 1;

        for (int i = x; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] nums = new int[x + 2];
        nums[0] = 1;
        return nums;
    }
}
