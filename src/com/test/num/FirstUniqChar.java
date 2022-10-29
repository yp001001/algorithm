package com.test.num;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。
如果不存在，则返回 -1。
 */

// 看见重复用hash
public class FirstUniqChar {

    public static void main(String[] args) {
        firstUniqChar("leetcode");
    }

    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }
        // hashmap保存数据是无序的！！！
        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return i;
            }
        }
        return -1;
    }
}
