package zuo.package14;

import java.util.*;

public class Problem01 {


    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) return -1;
        int bag8 = apple >> 3;
        int rest = apple - bag8 << 3;
        while (bag8 >= 0) {
            if (rest % 6 == 0) {
                return bag8 + (rest / 6);
            }
            rest += 8;
            bag8--;
        }
        return -1;
    }


    public static int whoWin(int n) {
        int want = 1;
        while (want <= n) {
            // 当前的先手是下一次的后手
            if (whoWin(n - want) == 2) {
                return 1;
            }
            if (want <= (n >> 2)) want <<= 2;
            else break;
        }
        return 2;
    }


    public static boolean isMsum(int num) {
        return num == (num & (~num + 1));
        // 或者 num == (num & (-num));
    }


    public static int process(int[] d, int[] p, int index, int nowd) {
        if (index == d.length) return 0;
        if (nowd < d[index]) {
            return p[index] + process(d, p, index + 1, nowd + d[index]);
        } else {
            return
                    Math.min(p[index] + process(d, p, index + 1, nowd + d[index]),
                            process(d, p, index + 1, nowd + p[index]));
        }
    }

    // 打怪兽
    public static int dp(int[] d, int[] p) {
        int N = d.length;
        int sum = 0;
        for (int i : p) {
            sum += i;
        }
        int[][] dp = new int[N][sum + 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
        // 表示钱数能得到的最大d
        dp[0][p[0]] = d[0];
        for (int i = 1; i < d.length; i++) {
            for (int j = 0; j <= sum; j++) {
                // j - p[i] 不越界，并且在钱数为 j - p[i]时，要能通过 0 - i-1的怪兽，并且钱数组合是有效的
                if (j >= p[i] && dp[i - 1][j - p[i]] != -1) {
                    dp[i][j] = dp[i - 1][j - p[i]] + d[i];
                }
                if (dp[i - 1][j] >= d[i]) {
                    // 选武力值最大的
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= sum; i++) {
            if (dp[d.length - 1][i] != -1) {
                ans = i;
                break;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
//        System.out.println(Integer.toBinaryString(-20));
//        System.out.println(Integer.toBinaryString(20));
//        System.out.println(Integer.toBinaryString(~-20 + 1));
//        System.out.println(Integer.toBinaryString(-20 & (20)));
//        for (int i = 0; i < 10000; i++) {
//            int[][] arr = randomArr(1000);
//            if (!(dp(arr[0], arr[1]) == process(arr[0], arr[1], 0, 0))) {
//                System.out.println("d[]：" + Arrays.toString(arr[0]));
//                System.out.println("p[]：" + Arrays.toString(arr[1]));
//                System.out.println("dp answer :" + dp(arr[0], arr[1]));
//                System.out.println("暴力解 answer :" + process(arr[0], arr[1], 0, 0));
//                System.out.println("error");
//                return;
//            }
//        }
    }


    public static int[][] randomArr(int N) {
        Random random = new Random();
        int size = random.nextInt(N) + 1;
        int big = random.nextInt(N) + 1;
        int[][] arr = new int[size][2];
        for (int i = 0; i < size; i++) {
            arr[i][0] = random.nextInt(big);
        }
        for (int i = 0; i < size; i++) {
            arr[i][1] = random.nextInt(big);
        }
        return arr;
    }
}
