package topic.package05;

import java.util.Arrays;

// 给定两个字符串s1,s2，问s2最少删除多少字符可以成为s1的子串
public class Problem04 {


    // 题目：
    // 给定两个字符串s1和s2，问s2最少删除多少字符可以成为s1的子串？
    // 比如 s1 = "abcde"，s2 = "axbc"
    // 返回 1

    // 解法一
    // 求出str2所有的子序列，然后按照长度排序，长度大的排在前面。
    // 然后考察哪个子序列字符串和s1的某个子串相等(KMP)，答案就出来了。
    // 分析：
    // 因为题目原本的样本数据中，有特别说明s2的长度很小。所以这么做也没有太大问题，也几乎不会超时。
    // 但是如果某一次考试给定的s2长度远大于s1，这么做就不合适了。


    // x字符串只通过删除的方式，变到y字符串
    // 返回至少要删几个字符
    // 如果变不成，返回Integer.Max
    public static int onlyDelete2(char[] x, char[] y) {
        if (Arrays.equals(x, y)) return 0;
        if (x.length < y.length) {
            return Integer.MAX_VALUE;
        }
        int N = x.length;
        int M = y.length;
        // dp[i][j] 表示x的前i个字符能否删除得到y
        int[][] dp = new int[N + 1][M + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        for (int i = 0; i <= N; i++) {
            dp[i][0] = i;
        }
        // 只需要一半
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                // 表示前i - 1个字符得到j
                if (dp[i - 1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = dp[i - 1][j] + 1;
                }
                if (x[i - 1] == y[j - 1] && dp[i - 1][j - 1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }
            }
        }
        return dp[N][M];
    }


    public static int onlyDelete(char[] x, char[] y) {
        if (x.length < y.length) {
            return Integer.MAX_VALUE;
        }
        int N = x.length;
        int M = y.length;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;
        // dp[i][j]表示前缀长度
        for (int i = 1; i <= N; i++) {
            dp[i][0] = i;
        }
        for (int xlen = 1; xlen <= N; xlen++) {
            for (int ylen = 1; ylen <= Math.min(M, xlen); ylen++) {
                if (dp[xlen - 1][ylen] != Integer.MAX_VALUE) {
                    dp[xlen][ylen] = dp[xlen - 1][ylen] + 1;
                }
                if (x[xlen - 1] == y[ylen - 1] && dp[xlen - 1][ylen - 1] != Integer.MAX_VALUE) {
                    dp[xlen][ylen] = Math.min(dp[xlen][ylen], dp[xlen - 1][ylen - 1]);
                }
            }
        }
        return dp[N][M];
    }

    public static String generateRandomString(int l, int v) {
        int len = (int) (Math.random() * l);
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ('a' + (int) (Math.random() * v));
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {

        char[] x = {'a', 'b', 'c', 'd'};
        char[] y = {'a', 'x'};

        System.out.println(onlyDelete(x, y));

        int str1Len = 20;
        int str2Len = 10;
        int v = 5;
        int testTime = 10000;
        boolean pass = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            String str1 = generateRandomString(str1Len, v);
            String str2 = generateRandomString(str2Len, v);
            int ans1 = onlyDelete(str1.toCharArray(), str2.toCharArray());
            int ans2 = onlyDelete(str1.toCharArray(), str2.toCharArray());
            if (ans1 != ans2) {
                pass = false;
                System.out.println(str1);
                System.out.println(str2);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("oops");
                break;
            }
        }
        System.out.println("test pass : " + pass);
    }
}
