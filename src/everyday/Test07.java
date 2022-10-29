package everyday;

// https://leetcode.cn/problems/number-of-smooth-descent-periods-of-a-stock/submissions/
public class Test07 {

    public static long dp(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        long[] dp = new long[prices.length];
        dp[0] = 1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) dp[i] = dp[i - 1] + 1;
            else dp[i] = 1;
        }
        int ans = 0;
        for (int i = 0; i < dp.length; i++) {
            ans += dp[i];
        }
        return ans;
    }

    public long dp2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        long dp = 1;
        long ans = 1;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] == prices[i - 1] - 1) dp = dp + 1;
            else dp = 1;
            ans += dp;
        }
        return ans;
    }

    public static long getDescentPeriods_window(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int l = 0, r = 1;
        long ans = 0;
        while (r < prices.length) {
            if (prices[r - 1] - 1 != prices[r]) {
                ans += (long) (r - l) * (r - l + 1) / 2;
                l = r;
                r++;
            } else {
                r++;
            }
        }
        ans += (long) (r - l) * (r - l + 1) / 2;
        return ans;
    }

}
