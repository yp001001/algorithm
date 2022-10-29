package everyday;

import zuo.package09.KMP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.cn/problems/string-matching-in-an-array/
public class Test17 {

    public static void main(String[] args) {
        stringMatching(new String[]{"leetcoder", "leetcode", "od", "hamlet", "am"});
    }

    public static List<String> stringMatching(String[] words) {
        if (words == null || words.length == 0) return null;
        Set<String> ans = new HashSet<>();
        for (String s1 : words) {
            for (String s2 : words) {
                if (s1.equals(s2) || ans.contains(s1)) continue;
                if (getIndexOf(s2, s1) != -1) ans.add(s1);
            }
        }
        return new ArrayList<>(ans);
    }


    public static int getIndexOf(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNextArray(str2);
        int i = 0, j = 0;
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else if (next[j] == -1) {
                i++;
            } else {
                j = next[j];
            }
        }
        return j == str2.length ? i - j : -1;
    }

    public static int[] getNextArray(char[] str) {
        if (str.length == 1) return new int[]{-1};
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int cn = 0, i = 2;
        while (i < str.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

}
