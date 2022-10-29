package topic.package18;

import java.util.Arrays;

// https://leetcode.cn/problems/gaM7Ch/
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        long[][] dp = new long[n + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amount] >= Integer.MAX_VALUE ? -1 : (int) dp[n][amount];
    }

}
