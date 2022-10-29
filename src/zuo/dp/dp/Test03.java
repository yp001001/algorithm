package zuo.dp.dp;

// 0-1 背包问题
public class Test03 {

    public static int maxValue1(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length == 0 || v.length == 0) return 0;
        return process1(w, v, 0, bag);
    }

    private static int process1(int[] w, int[] v, int index, int bag) {
        if (bag < 0) return 0;
        if (index == w.length) return 0;
        int p1 = process1(w, v, index + 1, bag);
        int p2 = 0;
        if (bag >= w[index]) {
            p2 = process1(w, v, index + 1, bag - w[index]) + v[index];
        }
        return Math.max(p1, p2);
    }


    public static int maxValue2(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length == 0 || v.length == 0 || w.length != v.length) return 0;
        int n = w.length;
        int[][] dp = new int[n + 1][bag + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= bag; j++) {
                int p1 = dp[i + 1][j];
                int p2 = j >= w[i] ? dp[i + 1][j - w[i]] + v[i] : -1;
                dp[i][j] = Math.max(p1, p2);
            }
        }
        // 当背包剩余容量为0时
        return dp[0][bag];
    }


    public static void main(String[] args) {
        int[] weight = {3, 2, 4, 7};
        int[] values = {5, 6, 3, 19};
        int bag = 11;
        System.out.println(maxValue1(weight, values, bag));
        System.out.println(maxValue2(weight, values, bag));
    }
}
