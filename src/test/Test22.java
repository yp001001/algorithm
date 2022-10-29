package test;

import java.util.*;

public class Test22 {
    public static void main(String[] args) {
        double d = 10d / 3;
        System.out.println(d);
    }

    /**
     * 最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        return -1;
    }

    /**
     * 最长公共子序列
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                if (c == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }


    public int maxSubArray_2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (res <= 0) res = nums[i];
            else {
                res += nums[i];
            }
            max = Math.max(res, max);
        }
        return max;
    }


    /**
     * 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum <= 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * 等差数列划分
     *
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int t = nums[1] - nums[0];
        int g = 1;
        int count = 0;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == t) {
                count += g;
                g++;
            } else {
                t = nums[i] - nums[i - 1];
                g = 1;
            }
        }
        return count;
    }

    /**
     * 小行星碰撞
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (deque.isEmpty() || isValid(deque.peek(), asteroids[i])) deque.push(asteroids[i]);
            else {
                boolean flag = true;
                while (!deque.isEmpty()) {
                    int m = deque.peek();
                    if (isValid(m, asteroids[i])) break;
                    else if (Math.abs(m) > Math.abs(asteroids[i])) {
                        flag = false;
                        break;
                    } else if (Math.abs(m) == Math.abs(asteroids[i])) {
                        flag = false;
                        deque.pop();
                        break;
                    } else deque.pop();
                }
                if (flag) {
                    deque.push(asteroids[i]);
                }
            }
        }
        int[] result = new int[deque.size()];
        int idx = deque.size() - 1;
        while (!deque.isEmpty()) {
            result[idx--] = deque.removeFirst();
        }
        return result;
    }

    private boolean isValid(int a, int b) {
        return !(a > 0 && b < 0);
    }


    /**
     * 最小时间差
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        if (n > 1440) {
            return 0;
        }
        Collections.sort(timePoints);
        int ans = Integer.MAX_VALUE;
        int time = getMinutes(timePoints.get(0));
        int prev = time;
        for (int i = 1; i < timePoints.size(); i++) {
            int minutes = getMinutes(timePoints.get(i));
            ans = Math.min(ans, minutes - prev);    // 减去相邻元素
            prev = minutes;
        }
        ans = Math.min(ans, time + 1440 - prev);
        return ans;
    }

    public int getMinutes(String t) {
        return (t.charAt(0) * 10 + t.charAt(1)) * 60 + (t.charAt(3) * 10 + t.charAt(4));
    }
}
