package com.test.string;

import java.util.HashSet;
import java.util.Set;

/*
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

输入: s = "abcabcbb"
输出: 3
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 */
public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        String s = "hellojavac++python";
        System.out.println(lengthOfLongestSubstring(s));
    }


    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();
        int length = s.length();
        int lm = 0;
        int ans = 0;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (lm < length && !set.contains(s.charAt(lm))) {
                set.add(s.charAt(lm));
                lm++;
            }
            ans = Math.max(ans, lm-i);
        }
        return ans;
    }


    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }
}
