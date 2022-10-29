package zuo.dp.dp;

// 数组分成两组平均数的较小值
public class Test15 {

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        return process(arr, 0, sum >> 1);
    }

    private static int process(int[] arr, int index, int rest) {
        if (index == arr.length) return 0;
        else {
            int p1 = process(arr, index + 1, rest);
            int p2 = 0;
            if (arr[index] <= rest) {
                p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
            }
            return Math.max(p1, p2);
        }
    }


    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int sum = 0;
        for (int a : arr) {
            sum += a;
        }
        int n = arr.length;
        sum /= 2;
        int[][] dp = new int[n + 1][sum + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (arr[i] <= rest) dp[i][rest] = Math.max(dp[i][rest], dp[i][rest - arr[i]] + arr[i]);
            }
        }
        return dp[0][sum];
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, 2, 4, 2, 1, 9, 18, 7, 20};
        System.out.println(right(arr));
        System.out.println(dp(arr));
    }
}
