package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test36 {


    public static void main(String[] args) {
        Test36 test36 = new Test36();
        test36.coinChange(new int[]{1, 2, 5}, 11);

    }


    /**
     * 三角形最小路径之和
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] dp = new int[n][n];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle.get(i).get(0);
        }
        for (int i = 1; i < n; i++) {
            int m = triangle.get(i).size();
            for (int j = 1; j < m; j++) {
                if (j == m - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }
        int minStep = Integer.MAX_VALUE;
        for (int x : dp[n - 1]) {
            minStep = Math.min(minStep, x);
        }
        return minStep;
    }

    /**
     * 优化：使用O(n)的空间复杂度
     */
    public int minimumTotal_2(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }


    /**
     * 分割等和子集
     *
     * @param nums
     * @return
     */
    /**
     * 01背包问题，是否满足nums总和的一半
     */
    public boolean canPartition(int[] nums) {
        int maxVal = 0;
        int sum = 0;
        for (int num : nums) {
            maxVal = Math.max(num, maxVal);
            sum += num;
        }
        int target = sum / 2;
        if (maxVal > target || sum % 2 != 0 || nums.length <= 1) return false;
        int n = nums.length;
        boolean[][] dp = new boolean[n][target + 1];    // 表示从0-i中选择一些数的和是否等于j
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        dp[0][nums[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= target; j++) {
                if (nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
        }
        return dp[n - 1][target];
    }


    /**
     * 加减的目标值
     *
     * @param nums
     * @param target
     * @return
     */
    int count = 0;

    // 使用回溯
    public int findTargetSumWays(int[] nums, int target) {
        dfs(nums, 0, target, 0);
        return count;
    }

    private void dfs(int[] nums, int path, int target, int sum) {
        if (nums.length == path) {
            if (target == sum) {
                count++;
            }
            return;
        }
        dfs(nums, path + 1, target, sum - nums[path]);
        dfs(nums, path + 1, target, sum + nums[path]);
    }

    /**
     * 使用动态规划（pos-neg=target  pos+neg=sum(nums) => pos = (sum(nums)+target)/2） => 01背包问题
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays_2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum - target) < 0 || (sum - target) % 2 != 0) return 0;
        int pos = (sum - target) / 2;
        int size = nums.length;
        int[][] dp = new int[size + 1][pos + 1]; // 表示0-i之间是否能转组成j
        dp[0][0] = 1;
        for (int i = 0; i <= size; i++) {
            for (int j = 0; j <= pos; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) {
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[size][pos];
    }


    /**
     * 最小的硬币数量
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        long[][] dp = new long[n + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amount] >= Integer.MAX_VALUE ? -1 : (int) dp[n][amount];
    }

    /**
     * 排列数目
     *
     * @param nums
     * @param target
     * @return
     */
    Map<Integer, Integer> map = new HashMap<>();

    /**
     * ②：完全背包
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < i) {
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }

    /**
     * ①：减枝
     */
    private int dfs(int[] nums, int target, int path) {
        if (target == 0) {
            return 1;
        }
        if (target < 0 || path >= nums.length) {
            return 0;
        }
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += dfs(nums, target - nums[i], i);
        }
        map.put(target, res);
        return res;
    }

}
