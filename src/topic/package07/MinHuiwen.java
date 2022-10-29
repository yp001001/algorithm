package topic.package07;

//https://leetcode.cn/problems/minimum-insertion-steps-to-make-a-string-palindrome/
public class MinHuiwen {
    public static void main(String[] args) {
        System.out.println(minInsertionsOneWay("zzazzz"));
    }

    public static int minInsertions(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[][] dp = new int[n][n];
        char[] str = s.toCharArray();
        for (int i = 0; i < n - 1; i++) {
            if (str[i] == str[i + 1]) dp[i][i + 1] = 0;
            else dp[i][i + 1] = 1;
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i + 1][j] + 1);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static String minInsertionsOneWay(String s) {
        if (s == null || s.length() == 0) return null;
        int n = s.length();
        int[][] dp = new int[n][n];
        char[] str = s.toCharArray();
        for (int i = 0; i < n - 1; i++) {
            if (str[i] == str[i + 1]) dp[i][i + 1] = 0;
            else dp[i][i + 1] = 1;
        }
        for (int i = n - 3; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i + 1][j] + 1);
                if (str[i] == str[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i + 1][j - 1]);
                }
            }
        }
        int L = 0, R = str.length - 1;
        char[] ans = new char[str.length + dp[L][R]];
        int ansl = L, ansr = ans.length - 1;
        while (L < R) {
            if (dp[L][R - 1] + 1 == dp[L][R]) {
                ans[ansl++] = str[R];
                ans[ansr--] = str[R--];
            } else if (dp[L + 1][R] + 1 == dp[L][R]) {
                ans[ansr--] = str[L];
                ans[ansl++] = str[L++];
            } else {
                ans[ansl++] = str[L++];
                ans[ansr--] = str[R--];
            }
        }
        if (L == R) {
            ans[ansl] = str[L];
        }
        return String.valueOf(ans);
    }
}
