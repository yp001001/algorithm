package zuo.dp.dp;

// 整数拆分
public class Test14 {

    public static int ways(int N) {
        if (N < 0) return 0;
        if (N == 1) return 1;
        return process(N, 1);
    }

    private static int process(int n, int number) {
        if (n == 0) return 1;
        if (number > n) return 0;
        int ways = 0;
        for (int i = 0; i * number <= n; i++) {
            ways += process(n - number * i, number + 1);
        }
        return ways;
    }

    public static int dp(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }


    public static void main(String[] args) {
        System.out.println(ways(13));
        System.out.println(dp(13));
    }
}
