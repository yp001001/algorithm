package zuo.package08;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 窗口内最大最小值
 */
public class DoubleQueue {

    public static int[] maxValue(int[] arr, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[arr.length - k + 1];
        for (int i = 0; i < arr.length; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] <= arr[i]) {
                deque.removeLast();
            }
            while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.removeFirst();
            }
            deque.addLast(i);
            if (!deque.isEmpty() && i >= k - 1) {
                ans[i - k + 1] = arr[deque.peekFirst()];
            }
        }
        return ans;
    }


    // 数组中子数组个数中的最大-最小值小于等于sum的个数
    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum <= 0) return 0;
        int N = arr.length;
        int R = 0;
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        int count = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {
                while (!maxDeque.isEmpty() && arr[maxDeque.peekLast()] <= arr[R]) {
                    maxDeque.removeLast();
                }
                while (!minDeque.isEmpty() && arr[minDeque.peekLast()] >= arr[R]) {
                    minDeque.removeLast();
                }
                maxDeque.addLast(R);
                minDeque.addLast(R);
                if (arr[maxDeque.peekFirst()] - arr[minDeque.peekFirst()] > sum) break;
                R++;    // R还需要，因为这个不符合条件，需要留到下一次循环
            }
            count += R - L;
            if (maxDeque.peekFirst() == L) maxDeque.pollFirst();
            if (minDeque.peekFirst() == L) minDeque.pollFirst();
        }
        return count;
    }

    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum <= 0) return 0;
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) count++;
            }
        }
        return count;
    }


    // 加油站问题
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int[] nums = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            nums[i] = gas[i] - cost[i];
        }
        int[] arr = new int[gas.length * 2];
        arr[0] = nums[0];
        for (int i = 1; i < gas.length; i++) {
            arr[i] = arr[i - 1] + nums[i];
        }
        for (int i = 0; i < gas.length; i++) {
            arr[i + gas.length] = arr[gas.length + i - 1] + nums[i];
        }
        Deque<Integer> deque = new ArrayDeque<>();
        int pre = 0;
        //...
        return 1;
    }


    // 最少硬币问题
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount == 0) return 0;
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        Arrays.fill(dp[n], Integer.MAX_VALUE);
        dp[n][0] = 0;
        for (int index = n - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest >= coins[index] && dp[index][rest - coins[index]] != Integer.MAX_VALUE)
                    dp[index][rest] = Math.min(dp[index][rest - coins[index]] + 1, dp[index + 1][rest]);
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};
        canCompleteCircuit(gas, cost);
    }
}
