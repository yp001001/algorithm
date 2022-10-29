package topic.package11;

/*
    给定两个字符串S和T
    返回S的所有子序列中
    有多少个子序列的字面值等于T
 */
// https://leetcode.cn/problems/distinct-subsequences/
public class MaxK {

    public static void main(String[] args) {
        System.out.println(numDistinct("abcca", "ac")); // 2
    }

    // 样本对应模型
    public static int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() == 0 || T.length() == 0) return 0;
        char[] source = S.toCharArray();
        char[] target = T.toCharArray();
        int N = source.length, M = target.length;
        // source的前i个字符对应target的前j个字符的num
        int[][] dp = new int[N][M];
        dp[0][0] = source[0] == target[0] ? 1 : 0;
        for (int i = 1; i < source.length; i++) {
            dp[i][0] += dp[i - 1][0];
            if (source[i] == target[0]) dp[i][0]++;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                dp[i][j] = dp[i - 1][j];
                if (source[i] == target[j]) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        return dp[N - 1][M - 1];
    }

}
