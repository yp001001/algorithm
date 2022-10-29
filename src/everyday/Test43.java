package everyday;

import java.util.Arrays;

public class Test43 {

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (x1, x2) -> x1[1] - x2[1]);
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

}
