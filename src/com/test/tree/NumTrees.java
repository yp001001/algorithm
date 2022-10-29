package com.test.tree;

/*
给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的
二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 */
public class NumTrees {

    public static void main(String[] args) {
        int i = numTrees(   11);
        System.out.println(i);
    }

    public static int numTrees(int n) {
        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for (int i = 2; i < nums.length; i++) {
            for (int j = 1; j <= i; j++) {
                nums[i] += nums[j - 1] * nums[i - j];
            }
        }
        return nums[n];
    }
}
