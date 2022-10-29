package zuo.dp.dp;

// 打死怪兽的概率
public class Test12 {

    public static double right1(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) return 0;
        long all = (long) Math.pow(M + 1, k);
        long kill = process1(N, M, k);
        return ((double) kill / (double) all);
    }

    // 怪兽还剩N点血
    // 每次的伤害在（0-M）范围上
    // 还有k次可以砍
    // 返回砍死的情况数
    public static long process1(int N, int M, int k) {
        // 求概率
        if (k == 0) return N <= 0 ? 1 : 0;
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways = process1(N, i, k - 1);
        }
        return ways;
    }


    public static double dp(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) return 0;
        // 有k刀可以砍，血量为N
        long[][] dp = new long[k + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= k; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                int ways = 0;
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        ways += dp[times - 1][hp - i];
                    } else {
                        ways += (long) Math.pow(M + 1, times);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        long all = (long) Math.pow(N + 1, k);
        long kill = dp[k][N];
        return ((double) kill / (double) all);
    }


    public static double dp2(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) return 0;
        long[][] dp = new long[k + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= k; times++) {
            dp[times][0] = (long) Math.pow((M + 1), k);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - M - 1 >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - M - 1];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long all = (long) Math.pow(N + 1, k);
        long kill = dp[k][N];
        return ((double) kill / (double) all);
    }

    public static void main(String[] args) {
    }
}
