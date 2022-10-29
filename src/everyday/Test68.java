package everyday;

import java.util.Arrays;
import java.util.TreeSet;

// https://leetcode.cn/problems/maximum-ascending-subarray-sum/
public class Test68 {

    public int maxAscendingSum(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int maxNum = Integer.MIN_VALUE;
        int now = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[i - 1]) {
                now += nums[i];
            } else {
                now = nums[i];
            }
            maxNum = Math.max(maxNum, now);
        }
        return maxNum;
    }


    // 交错字符串
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        int n = s1.length();
        int m = s2.length();
        if (n + m < s3.length()) return false;
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            if (s1.charAt(i - 1) != s3.charAt(i - 1)) break;
            dp[i][0] = true;
        }
        for (int i = 1; i <= m; i++) {
            if (s2.charAt(i - 1) != s3.charAt(i - 1)) break;
            dp[0][i] = true;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
                        || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[n][m];
    }


    // https://leetcode.cn/problems/coin-change/
    // 完全背包问题
    public int coinChange(int[] coins, int amount) {
        // 表示是否能组成当前下标的金钱
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i >= coin) dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }


    // 零钱兑换II
    public static int change(int amount, int[] coins) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (coins[i] <= j) {
                    dp[i][j] += dp[i][j - coins[i]];
                }
            }
        }
        return dp[n][amount];
    }

    // https://leetcode.cn/problems/advantage-shuffle/
    public static int[] advantageCount(int[] nums1, int[] nums2) {
        // 优势洗牌：将大于该数的最小数作为索引
        int n = nums1.length;
        Integer[] idx1 = new Integer[n];
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx1[i] = i;
            idx2[i] = i;
        }
        // 得到的是排序后的下标
        Arrays.sort(idx1, (i, j) -> nums1[i] - nums1[j]);
        Arrays.sort(idx2, (i, j) -> nums2[i] - nums2[j]);
        int[] ans = new int[n];
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums1[idx1[i]] > nums2[idx2[left]]) {
                // 当前下标nums1元素大于nums2[left]
                ans[idx2[left]] = nums1[idx1[i]];
            } else {
                // 当前下标nums1元素小于等于nums2[left]，将nums2[right]赋予nums1下标
                ans[idx2[right]] = nums1[idx1[i]];
                right--;
            }
        }
        return ans;
    }


}
