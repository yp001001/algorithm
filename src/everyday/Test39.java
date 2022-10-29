package everyday;

import java.util.Arrays;

public class Test39 {

    public static void main(String[] args) {
        int count = longStr("eehabmbaceecdee", 2);
        System.out.println(count);
    }

    /*
     给定一个字符串 s 和一个整数 m ，请你找出 s 中的最长子串
     要求该子串中的每一字符出现次数都不少于 m 。返回这一子串的长度。
     */
    public static int longStr(String s, int m) {
        if (s == null || s.length() == 0 || m <= 0) return -1;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        int[] tmp = new int[26];
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (count[index] != 0 && count[index] < m) {
                if (isValid(tmp, m)) {
                    max = Math.max(count(tmp), max);
                }
                subArr(tmp, count);
                Arrays.fill(tmp, 0);
                count[index] = 0;
            } else {
                tmp[index]++;
            }
        }
        return Math.max(count(count), max);
    }

    private static boolean isValid(int[] tmp, int m) {
        for (int i : tmp) {
            if (i != 0 && i < m) return false;
        }
        return true;
    }

    private static void subArr(int[] tmp, int[] count) {
        for (int i = 0; i < 26; i++) {
            count[i] -= tmp[i];
        }
    }

    private static int count(int[] count) {
        int sum = 0;
        for (int i = 0; i < count.length; i++) {
            sum += count[i];
        }
        return sum;
    }

}
