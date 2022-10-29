package zuo.dp.dp;

// 组合总数
public class Test10 {

    public static int ways(int[] arr, int[] count, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        return process(arr, count, aim, 0);
    }

    private static int process(int[] arr, int[] count, int aim, int index) {
        if (aim == 0) return 1;
        if (index == arr.length) return 0;
        int ways = 0;
        for (int i = 0; i <= count[index] && i * arr[index] <= aim; i++) {
            ways += process(arr, count, aim - i * arr[index], index + 1);
        }
        return ways;
    }

    public static int dp1(int[] arr, int[] count, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ways = 0;
                for (int c = 0; c * arr[index] <= rest && c <= count[index]; c++) {
                    ways += dp[index + 1][rest - arr[index] * c];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    public static int dp2(int[] arr, int[] count, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (count[index] > 0 && rest >= arr[index]) dp[index][rest] += dp[index][rest - arr[index]];
                if (rest >= (count[index] + 1) * arr[index])
                    dp[index][rest] -= dp[index + 1][rest - (count[index] + 1) * arr[index]];
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 10};
        int[] count = {10, 10, 10, 10};
        System.out.println(ways(arr, count, 63));
        System.out.println(dp1(arr, count, 63));
        System.out.println(dp2(arr, count, 63));
    }
}
