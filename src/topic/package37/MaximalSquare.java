package topic.package37;

// https://leetcode.cn/problems/maximal-square/
// 求最大正方形面积 dp
public class MaximalSquare {


    public static int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            dp[0][i] = matrix[0][i] - '0';
            ans = Math.max(dp[0][i], ans);
        }
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0] - '0';
            ans = Math.max(dp[i][0], ans);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    ) + 1;
                }
                ans = Math.max(dp[i][j], ans);
            }
        }
        return ans * ans;
    }

}
