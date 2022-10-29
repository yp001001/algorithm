package topic.package11;

public class GiveK {

    public static void main(String[] args) {
        System.out.println(isSum(new int[]{-2, -1, 2, 3}, -3));
    }

    public static boolean isSum(int[] arr, int k) {
        if (k == 0) return true;
        if (arr == null || arr.length == 0) return false;
        int max = 0, min = 0;
        for (int a : arr) {
            min = a < 0 ? min + a : min;
            max = a > 0 ? max + a : max;
        }
        int size = max - min + 1;
        boolean[][] dp = new boolean[arr.length][size + 1];
        dp[0][-min] = true;
        dp[0][arr[0] - min] = true;
        for (int i = 1; i < arr.length; i++) {
            for (int j = min; j <= max; j++) {
                dp[i][j - min] = dp[i - 1][j - min];
                int next = j - min - arr[i];
                // 判断越界
                if (next >= 0 && next <= max - min) {
                    dp[i][j - min] |= dp[i - 1][next];
                }
            }
        }
        return dp[arr.length - 1][k - min];
    }

}
