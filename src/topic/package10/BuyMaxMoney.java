package topic.package10;

public class BuyMaxMoney {


    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{3, 2, 3, 4}));
    }

    /*
        从左到右的范围尝试模型
    */
    public static int maxProfit(int k, int[] prices) {
        if (k == 0 || prices.length <= 1) return 0;
        int n = prices.length;
        int[][] dp = new int[n][k + 1];
        for (int j = 1; j <= k; j++) {
            // 先求出dp[1][j]
            int p1 = dp[0][j];
            int p2 = dp[1][j - 1] + prices[1] - prices[1];
            int p3 = dp[0][j - 1] + prices[1] - prices[0];
            dp[1][j] = Math.max(p1, Math.max(p2, p3));
            int best = Math.max(p2, p3) - prices[1];

            for (int i = 2; i < n; i++) {
                int newP = dp[i][j - 1] - prices[i];
                // 从上往下，修改best为最大值
                best = Math.max(newP, best);
                dp[i][j] = Math.max(dp[i - 1][j], best + prices[i]);
            }
        }
        return dp[n - 1][k];
    }
}
