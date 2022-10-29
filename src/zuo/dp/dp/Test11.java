package zuo.dp.dp;

// 在鸽子中走N步死亡的概率
public class Test11 {

    public static double probability(int N, int M, int row, int col, int k) {
        int ways = process(N, M, row, col, k);
        return ways / Math.pow(4, k);
    }

    private static int process(int N, int M, int row, int col, int k) {
        if (row < 0 || row >= N || col < 0 || col >= M) return 0;
        if (k == 0) return 1;
        int ways = 0;
        ways += process(N, M, row + 1, col, k - 1);
        ways += process(N, M, row - 1, col, k - 1);
        ways += process(N, M, row, col + 1, k - 1);
        ways += process(N, M, row, col - 1, k - 1);
        return ways;
    }


    public static double dp(int N, int M, int row, int col, int k) {
        int[][][] dp = new int[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][rest] += valid(dp, r, c - 1, rest - 1, N, M);
                    dp[r][c][rest] += valid(dp, r, c + 1, rest - 1, N, M);
                    dp[r][c][rest] += valid(dp, r - 1, c, rest - 1, N, M);
                    dp[r][c][rest] += valid(dp, r + 1, c, rest - 1, N, M);
                }
            }
        }
        int ways = dp[row][col][k];
        System.out.println(ways);
        return ways / Math.pow(4, k);
    }

    public static int valid(int[][][] dp, int i, int j, int k, int N, int M) {
        if (i < 0 || i >= N || j < 0 || j >= M) return 0;
        return dp[i][j][k];
    }


    public static void main(String[] args) {
        System.out.println(probability(10, 10, 0, 0, 10));
        System.out.println(dp(10, 10, 0, 0, 10));
    }
}
