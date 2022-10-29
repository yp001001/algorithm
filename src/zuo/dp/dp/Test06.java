package zuo.dp.dp;

// 字符串的最长回文子串
public class Test06 {

    public static int lpsl1(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] array = s.toCharArray();
        return f(array, 0, array.length - 1);
    }

    /* 表示求出str的L到R位置的最长回文子序列 */
    private static int f(char[] str, int L, int R) {
        // base case
        if (L == R) return 1;
        // base case
        if (L == R - 1) return str[L] == str[R] ? 2 : 1;
        int p1 = f(str, L + 1, R);
        int p2 = f(str, L, R - 1);
        int p3 = f(str, L + 1, R - 1);
        int p4 = 0;
        if (str[L] == str[R]) p3 = 2 + f(str, L + 1, R - 1);
        return Math.max(p4, Math.max(p1, Math.max(p2, p3)));
    }


    public static int lpsl2(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) return 1;
        char[] array = s.toCharArray();
        int N = array.length;
        int[][] dp = new int[N][N];
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = array[i] == array[i + 1] ? 2 : 1;
        }
        for (int L = N - 3; L >= 0; L--) {
            for (int R = L + 2; R < N; R++) {
                int p1 = dp[L + 1][R];
                int p2 = dp[L][R - 1];
                int p3 = 0;
                if (array[L] == array[R]) {
                    p3 = 2 + dp[L + 1][R - 1];
                }
                dp[L][R] = Math.max(p1, Math.max(p2, p3));
            }
        }

        return dp[0][N - 1];
    }


    public static void main(String[] args) {
        System.out.println(lpsl1("bbbab"));
        System.out.println(lpsl2("a"));
    }

}
