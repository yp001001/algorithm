package topic.package04;


// 返回一个数组中，选择的数字不能相邻的情况下最大子序列累加和
public class Problem04 {

    public static int maxValue(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int N = arr.length;
        if (N == 1) return arr[0];
        if (N == 2) return Math.max(arr[0], arr[1]);
        int[] dp = new int[N];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < N; i++) {
            int value = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
            dp[i] = Math.max(value, arr[i]);
        }
        return dp[N - 1];
    }

}
