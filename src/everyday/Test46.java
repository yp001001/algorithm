package everyday;

import java.util.Stack;


//https://leetcode.cn/problems/sum-of-subarray-minimums//*
public class Test46 {

    public static int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        int[] left = nearLessEqualsLeft(arr);
        int[] right = nearLessEqualsRight(arr);
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * arr[i];
            ans %= MOD;
        }
        return ans;
    }

    private static int[] nearLessEqualsLeft(int[] arr) {
        if (arr == null) return null;
        Stack<Integer> stack = new Stack<>();
        int[] left = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int index = stack.pop();
            left[index] = -1;
        }
        return left;
    }

    private static int[] nearLessEqualsRight(int[] arr) {
        int[] right = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = arr.length;
        }
        return right;
    }
}
