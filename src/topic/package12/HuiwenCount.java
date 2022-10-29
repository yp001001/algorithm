package topic.package12;

// 得到字符串有多少个回文子序列
// dp[i][j-1]
// dp[i+1][j-1]
// dp[i+1][j-1]
// dp[i][j]
public class HuiwenCount {

    public static void main(String[] args) {
        System.out.println(count("aba"));
    }


    public static int count(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int i = 0; i < N - 1; i++) {
            if (str[i] == str[i + 1]) dp[i][i + 1] = 3;
            else dp[i][i + 1] = 2;
        }
        for (int i = N - 3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                if (str[i] == str[j])
                    dp[i][j] += dp[i + 1][j - 1] + 1; // 有空串所以需要加1
                // 存在问题：不是非要 str[i] == str[j]，而是寻找子序列有多少回文
                // if (str[i] == str[j])    dp[i][j] += dp[i + 1][j - 1];
            }
        }
        return dp[0][N - 1];
    }

}
