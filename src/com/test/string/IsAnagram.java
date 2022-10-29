package com.test.string;

import java.util.HashMap;

/*
有效的字母异位词
 */
public class IsAnagram {

    public static void main(String[] args) {
        isAnagram("anagram", "nagaram");
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> m1 = new HashMap<>();
        for (char c : s.toCharArray()) {
            m1.put(c, m1.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            Integer integer = m1.get(c);
            if (integer == null || integer == 0) {
                return false;
            } else {
                m1.put(c, integer - 1);
            }
        }
        return true;

    }
}
