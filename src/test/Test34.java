package test;

import java.util.Arrays;
import java.util.Stack;

public class Test34 {

    public static void main(String[] args) {
        Test34 test34 = new Test34();
        boolean interleave = test34.isInterleave("ac", "d", "acd");
        System.out.println(interleave);
    }

    public boolean IsValidExp(String s) {
        // write code here
        Stack<Character> stack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (stack.isEmpty() || '(' == c || '[' == c || '{' == c) {
                stack.push(c);
            } else {
                char c2 = stack.pop();
                if (c == ')' && c2 == '(' || c == ']' && c2 == '[' || c == '}' && c2 == '{') {

                } else {
                    return false;
                }
            }
            idx++;
        }
        return stack.isEmpty();
    }


    public int GetCoinCount(int N) {
        // write code here
        int n = 4;
        int m = 1024 - N;
        int[] goods = new int[]{1, 4, 16, 64};
        int[] dp = new int[m + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                if (goods[i] > j) dp[j] = dp[j];
                else dp[j] = Math.min(dp[j], dp[j - goods[i]] + 1);
            }
        }
        return dp[m];
    }

    /**
     * 二进制间距
     *
     * @param n
     * @return
     */
    public int binaryGap(int n) {
        String s = toBinaryNum(n);
        int maxLength = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; i < s.length(); i++) {
            if (idx == -1 && s.charAt(i) == '1') {
                idx = i;
            } else if (s.charAt(i) == '1') {
                maxLength = Math.min(maxLength, i - idx);
                idx = i;
            }
        }
        return maxLength == Integer.MIN_VALUE ? 0 : maxLength + 1;
    }

    private String toBinaryNum(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 2);
            n = n / 2;
        }
        return sb.reverse().toString();
    }

    /**
     * 二进制间距解法2—位运算
     * **找到低位1，运算
     *
     * @param n
     * @return
     */
    public int binaryGap_2(int n) {
        int maxLength = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; n != 0; i++) {
            if ((n & 1) == 1) { // &的运算低于 +-，找到低位是否为1
                if (idx == -1) {
                    idx = i;
                } else {
                    maxLength = Math.max(i - idx, maxLength);
                    idx = i;
                }
            }
            n = n >> 1;
        }
        return maxLength == Integer.MIN_VALUE ? 0 : maxLength;
    }


    /**
     * 字符串交织
     * dp[i][j] 表示 s1 的前 i 个与 s2 的前 j 个能否交织成 s3 的 i+j 个
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[n][m];
    }


    /**
     * 最长回文子序列
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        // dp[i][j] 表示 i-j之间最长的回文子串
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {   // 前面的需要用到后面的，所以我们需要从从后面开始遍历
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }


    /**
     * 石子游戏
     * dp[i][j] 表示两个玩家石子数量差的最大值
     * dp[i][j] = Max{piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]}
     *
     * @return
     * @ param piles
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }

    /**
     * 路径的数目
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
