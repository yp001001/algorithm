package topic.package02;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Problem02 {

    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int N = nums.length;
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (max > nums[i]) {
                right = i;
            }
            max = Math.max(max, nums[i]);
        }
        int min = Integer.MAX_VALUE;
        int left = N;
        for (int i = N - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            }
            min = Math.min(min, nums[i]);
        }
        return Math.max(0, right - left + 1);
    }

    public static int findUnsortedSubarray2(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int max = arr[0];
        int leftIndex = -1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < max) {
                leftIndex = i;
            } else {
                // 得到左边较大值
                max = arr[i];
            }
        }
        if (leftIndex == -1) return 0;
        int min = arr[arr.length - 1];
        int rightIndex = -1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > min) {
                rightIndex = i;
            } else {
                min = arr[i];
            }
        }
        return Math.abs(leftIndex - rightIndex) + 1;
    }


    public static int[] randomArr(int N) {
        Random random = new Random();
        int size = random.nextInt(N) + 1;
        int big = random.nextInt(N) + 1;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(big);
        }
        return arr;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int[] arr = randomArr(10000);
            if (findUnsortedSubarray(arr) != findUnsortedSubarray2(arr)) {
                System.out.println("oops");
                return;
            }
        }
    }


    // 斐波那契数列的最长子串
    public int lenLongestFibSubseq2(int[] arr) {
        Map<Integer, Integer> indices = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            indices.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        int ans = 0;
        // 需要从后面到前面，后面需要前面的数据（前-后，则需要后面的数据，但是不能得到）
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[j] * 2 > arr[i]; j--) {
                int k = indices.getOrDefault(arr[i] - arr[j], -1);
                if (k >= 0) {
                    dp[j][i] = Math.max(dp[k][j] + 1, 3);
                }
                ans = Math.max(dp[j][i], ans);
            }
        }
        return ans;
    }
}
