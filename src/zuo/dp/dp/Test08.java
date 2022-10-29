package zuo.dp.dp;


// 组合总数（每个只有一个）
public class Test08 {

    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim == 0) return 0;
        return process(arr, aim, 0);
    }

//    private static int process(int[] arr, int aim, int path) {
//        if (aim < 0) return 0;
//        if (path == arr.length) return aim == 0 ? 1 : 0;
//        return process(arr, aim, path + 1) + process(arr, aim - arr[path], path + 1);
//    }

    private static int process(int[] arr, int aim, int path) {
        if (aim < 0) return 0;
        if (aim == 0) return 1;
        if (path == arr.length) return 0;
        return process(arr, aim, path + 1) + process(arr, aim - arr[path], path + 1);
    }

    public static int dp(int[] arr, int aim) {
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) dp[i][0] = 1;
        // path已经到底了，但是aim不为0，则此方案失败，返回0
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 1; rest <= aim; rest++) {
                dp[i][rest] = dp[i + 1][rest];
                if (rest >= arr[i]) dp[i][rest] += dp[i + 1][rest - arr[i]];
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 10};
        System.out.println(ways(arr, 13));
        System.out.println(dp(arr, 13));
    }
}
