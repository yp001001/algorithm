package test;

import java.util.*;

public class Test19 {
    public static void main(String[] args) {
        char a = Character.toLowerCase('A');        //字符大小写比较
//        Character.isLetterOrDigit('a');               //判断是否是数字或字符
        System.out.println(a);
    }

    public boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            while (!Character.isLetterOrDigit(c1)) {
                i++;
                c1 = s.charAt(i);
            }
            while (!Character.isLetterOrDigit(c2)) {
                j--;
                c2 = s.charAt(j);
            }
            if (Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 最长不重复的连续子串
     * 0 <= s.length <= 5 * 104
     * s 由英文字母、数字、符号和空格组成
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        // 滑动窗口
        int l = 0;
        int r = 0;
        int[] ctr = new int[128]; // 可以使用HashSet
        int idx;
        int maxLength = 0;
        while (r < s.length()) {
            idx = s.charAt(r) - ' ';
            // 表示前面无重复字符
            ctr[idx]++;
            if (ctr[idx] > 1) {
                while (ctr[idx] != 1) {
                    ctr[s.charAt(l++) - ' ']--;
                }
            }
            maxLength = Math.max(maxLength, r - l + 1);
            r++;
        }
        return maxLength;
    }


    /**
     * 字符串的所有变位词
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        // 滑动窗口
        int[] ctr1 = new int[26];
        int[] ctr2 = new int[26];
        int idx;
        int len = p.length();
        for (int i = 0; i < len; i++) {
            idx = s.charAt(i) - 'a';
            ctr1[idx]++;
            idx = p.charAt(i) - 'a';
            ctr2[idx]++;
        }
        List<Integer> list = new ArrayList<>();
        if (Arrays.equals(ctr1, ctr2)) {
            list.add(0);
        }
        idx = 0;
        for (int i = len; i < s.length(); i++) {
            ctr1[s.charAt(idx++) - 'a']--;
            ctr1[s.charAt(i) - 'a']++;
            if (Arrays.equals(ctr1, ctr2)) {
                list.add(idx);
            }
        }
        return list;
    }


    /**
     * substr()函数
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        for (int i = 0; i < (haystack.length() - needle.length()); i++) {
            boolean flag = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 首尾相连
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString2(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }

    /**
     * 双重for循环暴力
     *
     * @param s
     * @param goal
     * @return
     */
    public boolean rotateString(String s, String goal) {
        int m = s.length(), n = goal.length();
        if (m != n) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                // s.charAt((i + j) % n)
                if (s.charAt((i + j) % n) != goal.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串中的变位词
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        // 优化
        int[] ctr1 = new int[26];
        int[] ctr2 = new int[26];
        int s1Len = s1.length();
        int s2Len = s2.length();
        int idx;
        // 保存s1中每个字符的个数
        for (int i = 0; i < s1Len; i++) {
            idx = s1.charAt(i) - 'a';
            ctr1[idx]++;
            idx = s2.charAt(i) - 'a';
            ctr2[idx]++;
        }
        if (Arrays.equals(ctr1, ctr2)) {
            return true;
        }
        idx = 0;
        for (int i = s1Len; i < s2Len; i++) {
            ctr2[s2.charAt(idx) - 'a']--;
            ctr2[s2.charAt(i) - 'a']++;
            if (Arrays.equals(ctr1, ctr2)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 字符串的全排列
     *
     * @param s
     * @return
     */
    boolean[] marked;
    Set<String> set;

    public List<String> allString(String s) {
        int len = s.length();
        marked = new boolean[len];
        set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        dfs(s, 0, sb);
        return new ArrayList<>(set);
    }

    private void dfs(String s, int path, StringBuilder sb) {
        if (sb.length() == s.length()) {
            set.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (marked[i]) continue;
            marked[i] = true;
            sb.append(s.charAt(i));
            dfs(s, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
            marked[i] = false;
        }
    }
}
