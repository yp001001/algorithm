package test2;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test06 {

    /**
     * 最佳观光组合
     * **从前向后遍历，找到 v[i] + i 的最大值，与后面的 v[j]+j比较
     *
     * @param values
     * @return
     */
    public int maxScoreSightseeingPair(int[] values) {
        int res = values[0];    // 当前下标
        int max = 0;
        for (int i = 1; i < values.length; i++) {
            max = Math.max(values[i] + res - i, max);
            res = Math.max(values[i] + i, res);
        }
        return max;
    }

    /**
     * 买卖股票的最大时机
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int max = 0;    // 最大价值
        int minPoint = prices[0];   // 最低点
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i] - minPoint);
            minPoint = Math.min(minPoint, prices[i]);
        }
        return max;
    }


    /**
     * 买卖股票的最佳时机II
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;           // 表示不持有这个股票
        dp[0][1] = -prices[0];  // 表示持有这个股票
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(prices[i] + dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[n - 1][0] > dp[n - 1][1] ? dp[n - 1][0] : dp[n - 1][1];
    }

    /**
     * 买卖股票的最佳时机含冷冻期
     *
     * @param prices
     * @return
     */
    public int maxProfit_3(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][3];
        dp[0][0] = -prices[0];  // 持有股票
        dp[0][1] = 0;           // 表示不持有股票
        dp[0][2] = 0;           // 表示处于冷冻期
        for (int i = 1; i < n; i++) {
            //  持有该股票
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            // 不持有该股票
            dp[i][1] = Math.max(dp[i - 1][2], dp[i - 1][1]);
            // 处于冷冻期
            dp[i][2] = dp[i - 1][0] + prices[i];
        }
        return Math.max(dp[n - 1][2], Math.max(dp[n - 1][0], dp[n - 1][1]));
    }

    /**
     * 二维数组中的查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length && matrix[0][i] <= target; i++) {
            for (int j = 0; j < matrix[0].length && matrix[i][0] <= target; j++) {
                if (matrix[i][j] == target) return true;
                else if (matrix[i][j] > target) break;
            }
        }
        return false;
    }


    /**
     * 找到字符串中只出现一次的第一个字符
     *
     * @param s
     * @return
     */
    public char firstUniqChar(String s) {
        if (s.length() == 0) return ' ';
        Set<Character> set = new HashSet<>();
        int l = 0, r = 1;
        while (r < s.length()) {
            if (set.contains(s.charAt(l))) {
                l++;
                r = l + 1;
                continue;
            } else if (s.charAt(l) == s.charAt(r)) {
                set.add(s.charAt(l));
                l++;
                r = l + 1;
            } else {
                r++;
            }
        }
        char c = s.charAt(l);
        if (set.contains(c)) return ' ';
        return c;
    }


    /**
     * 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] flag = new boolean[len + 1];
        flag[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (flag[j] && set.contains(s.substring(j, i))) {
                    flag[i] = true;
                    break;
                }
            }
        }
        return flag[len];
    }

}
