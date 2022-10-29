package topic.package37;


// https://leetcode.cn/problems/maximum-length-of-repeated-subarray/
// 最长重复子数组
public class FindLength {

    public int findLength(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        // dp[i][j] 表示最长公共前缀
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

}
