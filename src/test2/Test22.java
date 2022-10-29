package test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test22 {

    public static void main(String[] args) {
        int i = new Test22().maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}});
        System.out.println(i);
    }


    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        s = s.trim();
        int i = s.length() - 1;
        int j = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                String substring = s.substring(i + 1, j + 1);
                sb.append(substring + " ");
                while (s.charAt(i) == ' ') {
                    i--;
                }
                j = i;
            }
            i--;
        }
        String substring = s.substring(i + 1, j + 1);
        sb.append(substring);
        return sb.toString();
    }

    /**
     * 字符串中所有的变位词
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
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
     * 俄罗斯套娃信封问题
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (x1, x2) -> x1[0] - x2[0] == 0 ? x1[1] - x2[1] : x1[0] - x2[0]);
        int count = 1;
        int w = envelopes[0][0], h = envelopes[0][1];
        for (int i = 1; i < envelopes.length; i++) {
            if (envelopes[i][0] > w && envelopes[i][1] > h) {
                count++;
                w = envelopes[i][0];
                h = envelopes[i][1];
            }
        }
        return count;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int max = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
