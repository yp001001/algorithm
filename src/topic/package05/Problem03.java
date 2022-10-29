package topic.package05;

// 编辑距离
public class Problem03 {

    // 1：i匹配j-1，加一个数
    // 2：i-1匹配j，减一个数
    // 3：i-1匹配j-1，①：i = j
    //              ②：i != j  修改一个数
    public static int minDistance(String word1, String word2) {
        return minCost(word1, word2, 1, 1, 1);
    }

    public static int minCost(String s1, String s2, int ic, int dc, int rc) {
        if (s1 == null || s2 == null) return 0;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = dc * i;
        }
        for (int i = 0; i <= M; i++) {
            dp[0][i] = ic * i;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = str1[i - 1] == str2[j - 1] ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + rc;
                dp[i][j] = min(dp[i][j], dp[i - 1][j] + dc, dp[i][j - 1] + ic);
            }
        }
        return dp[N][M];
    }

    static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
