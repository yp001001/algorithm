package zuo.dp.dp;

import java.util.Arrays;

// 最少硬币问题
public class Test13 {

    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) return 0;
        return process(coins, amount, 0);
    }

    private int process(int[] coins, int amount, int index) {
        if (amount == 0) return 0;
        if (amount < 0 || index == coins.length) return Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * coins[index] <= amount; zhang++) {
            int next = process(coins, amount - zhang * coins[index], index + 1);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }


    public static int dp1(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) return 0;
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        Arrays.fill(dp[n], Integer.MAX_VALUE);
        dp[n][0] = 0;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * coins[index]];
                    if (next != Integer.MAX_VALUE) ans = Math.min(zhang + next, ans);
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public static int dp2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) return 0;
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        Arrays.fill(dp[n], Integer.MAX_VALUE);
        dp[n][0] = 0;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= coins[index] && dp[index][rest - coins[index]] != Integer.MAX_VALUE)
                    dp[index][rest] = Math.min(dp[index][rest - coins[index]] + 1, dp[index][rest]);
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public static void main(String[] args) {
        int dp = dp1(new int[]{1, 3, 5}, 5);
        int dp2 = dp2(new int[]{1, 3, 5}, 5);
        System.out.println(dp);
        System.out.println(dp2);
    }
}
