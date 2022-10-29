package com.test.string;

import java.util.Arrays;

public class CanConstruct {

    // 击败33%
    public static boolean canConstruct(String ransomNote, String magazine) {
        char[] c1 = ransomNote.toCharArray();
        char[] c2 = magazine.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        int l1 = 0;
        int l2 = 0;
        int count = 0;
        while (l1 < c1.length && l2 < c2.length) {
            if (c1[l1] == c2[l2]) {
                l1++;
                l2++;
                count++;
            } else if (c1[l1] > c2[l2]) {
                l2++;
            } else {
                l1++;
            }
            if (count == c1.length) {
                return true;
            }
        }
        return false;
    }

    // Stringbuilder
    public static boolean canConstruct2(String ransomNote, String magazine) {
        StringBuilder stringBuilder = new StringBuilder(magazine);
        int index;
        for (char c : ransomNote.toCharArray()) {
            index = stringBuilder.indexOf(c + "");
            if (index >= 0) {
                stringBuilder.deleteCharAt(index);
            } else {
                return false;
            }
        }
        return true;
    }

}
