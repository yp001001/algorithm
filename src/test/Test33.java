package test;

import java.util.HashMap;
import java.util.Map;

public class Test33 {


    /**
     * 最长斐波那契数列
     *
     * @param arr
     * @return
     */
    public int lenLongestFibSubseq(int[] arr) {
        // key保存差，value保存数量
        int maxLength = 0;
        Map<Integer, Integer>[] maps = new Map[arr.length];

        for (int i = 0; i < arr.length; i++) {
            maps[i] = new HashMap<>();
            for (int j = i; j < arr.length; j++) {
                int value = maps[j].getOrDefault(arr[i] - arr[j], 0) + 1;
                maps[i].put(arr[j], value);
                maxLength = Math.max(maxLength, value);
            }
        }

        return maxLength == 1 ? 0 : maxLength + 1;
    }


    /**
     * 最长递增子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) != text2.charAt(j - 1)) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                else dp[i][j] = dp[i][j - 1] + 1;
            }
        }
        return dp[m][n];
    }

}
