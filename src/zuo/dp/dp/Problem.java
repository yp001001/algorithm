package zuo.dp.dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem {

    //==================================================不同路径============================

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //==================================================不同路径II============================

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        if (obstacleGrid[0][0] == 1) return 0;
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[i][0] == 1) continue;
            dp[i][0] = dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[0][i] == 1) continue;
            dp[0][i] = dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[n - 1][m - 1];
    }

    //==================================================最小路径之和============================

    public int minPathSum(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }


    //==================================================编辑距离============================

    public int minDistance(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        // dp[i][j] 表示当前word1的前i个字符与word2的前j个字符匹配需要的最少操作数
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int i = 0; i <= m; i++) dp[0][i] = i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[n][m];
    }

    int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


    //==================================================买卖股票的最佳时机============================

    public int maxProfit_1(int[] prices) {
        int n = prices.length;
        // 状态是持有股票或者不持有股票，k = 1
        int buy = 0, sell = 0;
        for (int i = 0; i < n; i++) {
            // 第一天不可能卖出股票
            if (i == 0) {
                sell = 0;
                buy = -prices[i];
            } else {
                sell = Math.max(buy + prices[i], sell);
                // 因为只能购买一次，所以是 Max(buy,-prices[i]);
                buy = Math.max(buy, -prices[i]);
            }
        }
        return sell;
    }


    //==================================================买卖股票的最佳时机II============================

    public int maxProfit_2(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
            } else {
                dp[i][0] = Math.max(dp[i - 1][1] + prices[i], dp[i - 1][0]);
                // 因为这可以购买多次，所以是Max(dp[i-1][1],dp[i-1][0]-prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][0];
    }

    public int maxProfit_optimize_space(int[] prices) {
        int n = prices.length;
        int buy = 0, sell = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                sell = 0;
                buy = -prices[i];
            } else {
                int temp = sell;
                sell = Math.max(buy + prices[i], sell);
                // 因为这可以购买多次，所以是Max(dp[i-1][1],dp[i-1][0]-prices[i]);
                buy = Math.max(buy, temp - prices[i]);
            }
        }
        return sell;
    }

    //==================================================买卖股票的最佳时机III（不明白）============================

    public int maxProfit_k(int[] prices) {
        int n = prices.length;
        int max_k = 2;
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        // 穷举了 n × max_k × 2 个状态，正确。
        return dp[n - 1][max_k][0];
    }


    //==================================================买卖股票的最佳时机含冷冻期============================

    public int maxProfit_(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;

        int[][] f = new int[n][3];
        // f[i][0]: 手上持有股票的最大收益
        // f[i][1]: 手上不持有股票，并且处于冷冻期中的累计最大收益
        // f[i][2]: 手上不持有股票，并且不在冷冻期中的累计最大收益
        f[0][0] = -prices[0];
        for (int i = 1; i < n; i++) {
            // 前一天没有卖，或者当天购买
            f[i][0] = Math.max(f[i - 1][0], f[i - 1][2] - prices[i]);
            // 处于冷冻期，前一天一定抛出股票
            f[i][1] = f[i - 1][0] + prices[i];
            // 不处于冷冻期，前一天一定没有抛出股票
            f[i][2] = Math.max(f[i - 1][2], f[i - 1][1]);
        }
        return Math.max(f[n - 1][2], f[n - 1][1]);
    }


    //==================================================买卖股票的最佳时机含手续费============================

    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[0][0] = 0;
                dp[0][1] = -prices[0] - fee;
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][0] - prices[i] - fee, dp[i - 1][1]);
            }
        }
        return dp[n - 1][0];
    }


    //==================================================地下层游戏（不清楚）============================

    int[][] memo;

    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return dp(dungeon, 0, 0);
    }

    /* dp[i][j] 表示到达该点的最小初始血量 */
    /* 定义：从（i,j）到达右下角，需要的初始血量至少是多少 */
    int dp(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i == m - 1 && j == n - 1) {
            return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
        }
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }
        // 避免重复计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        int res = Math.min(dp(grid, i, j + 1),
                dp(grid, i + 1, j)) - grid[i][j];
        memo[i][j] = res <= 0 ? 1 : res;
        return memo[i][j];
    }


    //==================================================最长公共子序列============================

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    //==================================================分割等和子集============================

    public boolean canPartition_dfs(int[] nums) {
        int target = 0;
        for (int num : nums) {
            target += num;
        }
        if (target % 2 == 1) return false;
        target /= 2;
        // dp[i][0]表示不拿该数，dp[i][1]表示拿该数
        boolean flag = dp(nums, target, 0);
        return flag;
    }

    Map<Integer, Boolean> dict = new HashMap<>();

    // 这种方式不太明白
    boolean dp(int[] nums, int target, int path) {
        if (target == 0) return true;
        if (path == nums.length || target < 0) return false;
        if (dict.containsKey(target)) return dict.get(target);
        boolean flag = dp(nums, target, path + 1) ||
                dp(nums, target - nums[path], path + 1);
        dict.put(target, flag);
        return flag;
    }


    /* 转换成01背包问题，背包容量为 target */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false;
        int n = nums.length;
        sum = sum / 2;
        // dp[i][j] = x 表示前i个物品，当前背包容量为j时，若x为true，则可以装满
        boolean[][] dp = new boolean[n + 1][sum + 1];
        // base case
        for (int i = 0; i <= n; i++) dp[i][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // 背包容量不足，不能装入背包
                if (j - nums[i - 1] < 0) dp[i][j] = dp[i - 1][j];
                    // 装入或不装入背包
                else dp[i][j] = dp[i - 1][j] || dp[i][j - nums[i - 1]];
            }
        }
        return dp[n][sum];
    }


    //==================================================最大正方形============================

    public int maximalSquare(char[][] matrix) {
        return -1;
    }


    //==================================================交错字符串============================

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        char[] chars3 = s3.toCharArray();
        return process(chars1, chars2, chars3, 0, 0, 0);
    }

    private boolean process(char[] chars1, char[] chars2, char[] chars3, int index1, int index2, int index3) {
        if (index1 == chars1.length && index2 == chars2.length) return true;

        boolean r1 = false, r2 = false;
        if (index1 < chars1.length && chars1[index1] == chars3[index3])
            r1 = process(chars1, chars2, chars3, index1 + 1, index2, index3 + 1);
        if (index2 < chars2.length && chars2[index2] == chars3[index3])
            r2 = process(chars1, chars2, chars3, index1, index2 + 1, index3 + 1);
        return r1 || r2;
    }


    public boolean dp2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t) {
            return false;
        }
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0)
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                if (j > 0)
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
            }
        }
        return f[n][m];
    }


    public static void main(String[] args) {
        Problem problem = new Problem();
        boolean interleave = problem.isInterleave("aabcc", "dbbca", "aadbbcbcac");
        System.out.println(interleave);
    }

}