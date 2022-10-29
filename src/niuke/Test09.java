package niuke;

import java.util.Scanner;
import java.util.Stack;

public class Test09 {

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = Integer.parseInt(sc.nextLine());
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = sc.nextInt();
//        }
//        maxZan(nums);
        maxZan(new int[]{1, 1, 2, 5, 7, 10});
    }

    public static String parse(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        char[] str = s.toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (!stack.isEmpty()) {
                if (str[i] == ')') {
                    stack.pop();
                }
                if (str[i] == '(') {
                    stack.push('(');
                }
            } else if (str[i] == '<') {
                if (sb.length() != 0) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                if (str[i] == '(') {
                    stack.push(str[i]);
                } else {
                    sb.append(str[i]);
                }
            }
        }
        return sb.toString();
    }


    public static void maxZan(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            System.out.println(nums[0]);
        } else if (n == 2) {
            System.out.println(nums[0] > nums[1] ? nums[0] : nums[1]);
        } else {
            int[] dp = new int[n + 1];
            int[] cnt = new int[n + 1];
            cnt[1] = cnt[2] = 1;
            dp[0] = 0;
            dp[1] = nums[0];
            dp[2] = nums[0] > nums[1] ? nums[0] : nums[1];
            int count = 1;
            for (int i = 3; i <= n; i++) {
                if (dp[i - 1] < dp[i - 2] + nums[i - 1]) {
                    dp[i] = dp[i - 2] + nums[i - 1];
                    cnt[i] = cnt[i - 2] + 1;
                    count++;
                } else {
                    dp[i] = dp[i - 1];
                    cnt[i] = cnt[i - 1];
                }

            }

            System.out.println(dp[n] + " " + cnt[n]);

        }
    }

}
