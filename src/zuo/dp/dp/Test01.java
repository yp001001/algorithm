package zuo.dp.dp;

import java.util.Arrays;

public class Test01 {

    public static int ways1(int N, int start, int aim, int K) {
        return process1(start, K, aim, N);
    }

    /**
     * @param cur  当前位置
     * @param rest 还需要走rest步
     * @param aim  最终目标是aim
     * @param N    有哪些位置
     * @return
     */
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) return cur == aim ? 1 : 0;
        if (cur == 1) return process1(cur + 1, rest - 1, aim, N);
        else if (cur == N) return process1(cur - 1, rest - 1, aim, N);
        else {
            return process1(cur + 1, rest - 1, aim, N) + process1(cur - 1, rest - 1, aim, N);
        }
    }


    public static int ways2(int N, int start, int aim, int K) {
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 0; i < K; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[aim][0] = 1;
        return process2(start, K, aim, N, dp);
    }

    private static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        if (rest == 0) return cur == aim ? 1 : 0;
        if (dp[cur][rest] != -1) return dp[cur][rest];
        int res;
        if (cur == 1) res = process1(cur + 1, rest - 1, aim, N);
        else if (cur == N) res = process1(cur - 1, rest - 1, aim, N);
        else {
            res = process1(cur + 1, rest - 1, aim, N) + process1(cur - 1, rest - 1, aim, N);
        }
        dp[cur][rest] = res;
        return res;
    }


    public static int ways3(int N, int start, int aim, int K) {
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        // 需要竖着比较
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                if (j == 1) dp[j][i] = dp[j + 1][i - 1];
                else if (j == N) dp[j][i] = dp[j - 1][i - 1];
                else dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        int ways1 = ways1(5, 2, 4, 6);
        int ways2 = ways2(5, 2, 4, 6);
        int ways3 = ways3(5, 2, 4, 6);
        System.out.println(ways1);
        System.out.println(ways2);
        System.out.println(ways3);
    }
}
