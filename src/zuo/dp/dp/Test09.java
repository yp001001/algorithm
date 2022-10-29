package zuo.dp.dp;

// 卡牌问题
public class Test09 {

    public static int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        return process(arr, aim, 0);
    }

//    private static int process(int[] arr, int aim, int index) {
//        if (aim < 0) return 0;
//        if (aim == 0) return 1;
//        if (index == arr.length) return 0;
//        // 有问题：例如，当我们需要2张的时候，回溯，下面就会产生重复
//        return process(arr, aim, index + 1) +
//                process(arr, aim - arr[index], index) +
//                process(arr, aim - arr[index], index + 1);
//    }

    private static int process(int[] arr, int aim, int index) {
        if (aim == 0) return 1;
        if (index == arr.length) return 0;
        int ways = 0;
        for (int count = 0; count * arr[index] <= aim; count++) {
            ways += process(arr, aim - arr[index] * count, index + 1);
        }
        return ways;
    }

    // 非记忆化搜索模式
    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ways = 0;
                for (int count = 0; count * arr[index] <= rest; count++) {
                    ways += dp[index + 1][rest - arr[index] * count];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }


    // 记忆化搜索模式
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) return 0;
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= arr[index]) dp[index][rest] += dp[index][rest - arr[index]];
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 5, 10};
        System.out.println(ways(nums, 172));
        System.out.println(dp1(nums, 172));
        System.out.println(dp2(nums,172));
    }
}
