package jiuzhang;

public class Test01 {

    public static void main(String[] args) {
    }

    /**
     * 最长回文子串
     * 区间型动态规划
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) return null;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;        // 单个字符为true
        }
        int start = 0;
        int longest = 1;
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                longest = 2;
            }
        }
        // dp[i][j] = dp[i+1][j-1] && charAt(i)==charAt(j)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                if (dp[i][j] && j - i + 1 > longest) {
                    longest = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + longest);
    }
}
