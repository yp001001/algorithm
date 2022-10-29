package test;

public class Test37 {


    /**
     * 计算比特位
     *
     * @param n
     * @return
     */
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = getNumBit(i);
        }
        return res;
    }

    private int getNumBit(int n) {
        int x = 0;
        while (n > 0) {
            n = n & (n - 1);
            x++;
        }
        return x;
    }


    /**
     * 整数拆分
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        int max = 0;
        for (int i = 2; i <= n; i++) {
            max = 0;
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max(j * (i - j), dp[i - j] * j));
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
