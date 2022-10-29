package zuo.dp.dp;

// 象棋跳跃到某一个位置方法数
public class Test07 {

    public static int ways1(int x, int y, int rest) {
        return jump(0, 0, rest, x, y);
    }

    // 当前来到的位置是(x,y)
    // 还剩下rest步需要走
    // 跳完rest步，正好跳到a,b方法数是多少
    public static int jump(int x, int y, int rest, int a, int b) {
        // base case
        if (x < 0 || x > 9 || y < 0 || y > 8) return 0;
        // base case
        if (rest == 0) {
            return x == a && y == b ? 1 : 0;
        }
        int ways = jump(x + 2, y + 1, rest - 1, a, b);
        ways += jump(x - 2, y + 1, rest - 1, a, b);
        ways += jump(x + 2, y - 1, rest - 1, a, b);
        ways += jump(x - 2, y - 1, rest - 1, a, b);
        ways += jump(x + 1, y + 2, rest - 1, a, b);
        ways += jump(x - 1, y + 2, rest - 1, a, b);
        ways += jump(x + 1, y - 2, rest - 1, a, b);
        ways += jump(x - 1, y - 2, rest - 1, a, b);
        return ways;
    }

    public static int ways2(int x, int y, int rest) {
        return dp(x, y, rest);
    }

    public static int dp(int a, int b, int k) {
        int[][][] dp = new int[10][9][k + 1];
        dp[a][b][0] = 1;
        // 由递归可得 rest依赖rest-1
        for (int rest = 1; rest <= k; rest++) {
            for (int x = 0; x < 10; x++) {
                for (int y = 0; y < 9; y++) {
                    int ways = pick(dp, x + 2, y + 1, rest - 1);
                    ways += pick(dp, x - 2, y + 1, rest - 1);
                    ways += pick(dp, x + 2, y - 1, rest - 1);
                    ways += pick(dp, x - 2, y - 1, rest - 1);
                    ways += pick(dp, x + 1, y + 2, rest - 1);
                    ways += pick(dp, x - 1, y + 2, rest - 1);
                    ways += pick(dp, x + 1, y - 2, rest - 1);
                    ways += pick(dp, x - 1, y - 2, rest - 1);
                    dp[x][y][rest] = ways;
                }
            }
        }

        return dp[0][0][k];
    }

    public static int pick(int[][][] dp, int x, int y, int rest) {
        if (x < 0 || x > 9 || y < 0 || y > 8) return 0;
        return dp[x][y][rest];
    }


    public static void main(String[] args) {
        int x = 7, y = 7;
        int step = 10;
        System.out.println(ways1(x, y, step));
        System.out.println(dp(x, y, step));
    }
}
