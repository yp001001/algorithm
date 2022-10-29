package niuke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test10 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] params = br.readLine().split(" ");
        int sum = Integer.parseInt(params[0]);
        String s = params[1].substring(1, params[1].length() - 1);
        String[] params1 = s.split(",");
        int[] coins = new int[params1.length];
        for (int i = 0; i < params1.length; i++) {
            coins[i] = Integer.parseInt(params1[i]);
        }
        System.out.println(change(sum, coins));
    }

    public static int change(int amount, int[] coins) {
        int n = coins.length;
        // 表示前i种类型硬币是否能组合成amount
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (coins[i - 1] <= amount) {
                    dp[i][j] += dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[n][amount];
    }

}
