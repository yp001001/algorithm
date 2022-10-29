package dp;

import java.util.Arrays;

// https://leetcode.cn/problems/coin-change-2/
public class Test03 {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(change(5, new int[]{1, 2, 5}));
    }

    public static int change(int amount, int[] coins) {
        int n = coins.length;
        // dp[i][j]表示前i种硬币是否能组成amount金额
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (coins[i - 1] <= j && dp[i][j - coins[i - 1]] != Integer.MAX_VALUE) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

}
