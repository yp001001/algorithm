package topic.package38;


// https://leetcode.cn/problems/palindromic-substrings/
public class CountSubstrings {

    // 回文子串的个数
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; i++) {
            int left = i / 2, right = i / 2 + i % 2;
            while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
                ans++;
                left--;
                right++;
            }
        }
        return ans;
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        int n = s.length();
        char[] str = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int start = 0;
        int longest = 1;
        for (int i = 1; i < n; i++) {
            if (str[i] == str[i - 1]) {
                dp[i - 1][i] = true;
                start = i - 1;
                longest = 2;
            }
        }
        // dp[i][j] = dp[i+1][j-1] && charAt(i) == charAt(j)
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                dp[i][j] = dp[i + 1][j - 1] && str[i] == str[j];
                if (dp[i][j] && j - i + 1 > longest) {
                    start = i;
                    longest = j - i + 1;
                }
            }
        }
        return s.substring(start, start + longest);
    }

}
