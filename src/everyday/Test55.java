package everyday;

import java.util.Stack;

public class Test55 {

    public static int sumSubarrayMins(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int MOD = 1_000_000_000 + 7;
        long sum = 0;
        int[] left = nearLessEqualsLeft(arr);
        int[] right = nearLessEqualsRight(arr);
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            sum += start * end * arr[i];
            sum %= MOD;
        }
        return (int) sum;
    }

    // 从左向右找到比该数大的下标
    private static int[] nearLessEqualsRight(int[] arr) {
        int n = arr.length;
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int index = stack.pop();
                right[index] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = arr.length;
        }
        return right;
    }

    // 从右向左找到比该数大的下标
    private static int[] nearLessEqualsLeft(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int index = stack.pop();
                left[index] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        return left;
    }

}
