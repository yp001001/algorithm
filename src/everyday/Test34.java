package everyday;

import java.util.Arrays;

// https://leetcode.cn/problems/longest-increasing-subsequence/
public class Test34 {

    public static void main(String[] args) {
        int length = lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6});
        System.out.println(length);
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int max = -1;
        for (int d : dp) {
            max = Math.max(d, max);
        }
        return max;
    }

}
