package zuo.package07;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Subsequence {

    public static int combinationSum4(int[] nums, int target) {
        return backtrack(nums, target);
    }

    private static int backtrack(int[] nums, int rest) {
        if (rest == 0) return 1;
        if (rest < 0) return 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += backtrack(nums, rest - nums[i]);
        }
        return ans;
    }

    public static int dp(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int rest = 1; rest <= target; rest++) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= rest)
                    dp[rest] += dp[rest - nums[i]];
            }
        }
        return dp[target];
    }


    // 字符串的全排列
    public static List<String> permutation(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) return ans;
        char[] array = s.toCharArray();
        g(array, ans, 0);
        return ans;
    }

    private static void g(char[] array, List<String> ans, int index) {
        if (index == array.length) {
            ans.add(String.valueOf(array));
        } else {
            for (int i = index; i < array.length; i++) {
                swap(array, index, i);
                g(array, ans, index + 1);
                swap(array, i, index);
            }
        }
    }

    private static void swap(char[] array, int i, int index) {
        char temp = array[i];
        array[i] = array[index];
        array[index] = temp;
    }


    // 字符串的全排列（去重版）
    public static List<String> permutation2(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) return ans;
        char[] array = s.toCharArray();
        g(array, ans, 0);
        return ans;
    }

    private static void g2(char[] array, List<String> ans, int index) {
        if (index == array.length) {
            ans.add(String.valueOf(array));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < array.length; i++) {
                if (!visited[array[i]]) {
                    visited[array[i]] = true;
                    swap(array, index, i);
                    g(array, ans, index + 1);
                    swap(array, i, index);
                }
            }
        }
    }


    // 返回值作为栈
    public static void reverseStack(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        int i = f(stack);
        reverseStack(stack);
        stack.push(i);
    }

    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }
        int last = f(stack);
        stack.push(result);
        return last;
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reverseStack(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
