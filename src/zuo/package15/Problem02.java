package zuo.package15;

// 四边形不等式（石子合并问题）
//  范围动态规划
public class Problem02 {

    public static int[] sum(int[] arr) {
        int N = arr.length;
        int[] sum = new int[N + 1];
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }
        return sum;
    }

    // 求出L-R的合并和
    public static int w(int[] sum, int l, int r) {
        return sum[r + 1] - sum[l];
    }


    public static int min1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int N = arr.length;
        int[] s = sum(arr);
        return process1(0, N - 1, s);
    }

    public static int process1(int L, int R, int[] s) {
        if (L == R) {
            return 0;
        }
        int next = Integer.MAX_VALUE;
        for (int leftEnd = L; leftEnd < R; leftEnd++) {
            next = Math.min(next, process1(L, leftEnd, s) + process1(leftEnd + 1, R, s));
        }
        return next + w(s, L, R);
    }

    public static int min2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        int[] sum = sum(arr);
        int[][] dp = new int[N][N];
        for (int l = N - 2; l >= 0; l--) {
            for (int r = l + 1; r < N; r++) {
                int next = Integer.MAX_VALUE;
                for (int leftEnd = l; leftEnd < r; leftEnd++) {
                    next = Math.min(next, dp[l][leftEnd] + dp[leftEnd + 1][r]);
                }
                dp[l][r] = next + w(sum, l, r);
            }
        }
        return dp[0][N - 1];
    }


    public static int[] randomArray(int len, int maxValue) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    public static void main(String[] args) {
        int N = 15;
        int maxValue = 100;
        int testTime = 1000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            int[] arr = randomArray(len, maxValue);
            int ans1 = min1(arr);
            int ans2 = min2(arr);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
