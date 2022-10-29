package niuke;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

/**
 * 腾讯面试真题
 */
public class Test01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 营业
        int[] work = new int[n];
        for (int i = 0; i < n; i++) {
            work[i] = sc.nextInt();
        }
        // 锻炼
        int[] sport = new int[n];
        for (int i = 0; i < n; i++) {
            sport[i] = sc.nextInt();
        }
        // dp[i][0] worker，dp[i][1] sport，dp[i][2] res/having
        int[][] dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], n + 1);
        }
        dp[0][0] = dp[0][1] = dp[0][2] = 0;
        for (int i = 1; i <= n; i++) {
            if (work[i - 1] == 1) {
                // 当前工作，前面的休息时间没有变，则是 min(dp[i - 1][1], dp[i - 1][2])
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]);
            }
            if (sport[i - 1] == 1) {
                // 当天运动，同上
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
            }
            // 当天休息， minValue + 1
            dp[i][2] = Math.min(dp[i - 1][0], Math.min(dp[i - 1][1], dp[i - 1][2])) + 1;
        }
        System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2])));
    }

    public String compress(String str) {
        // write code here
        Deque<String> stack = new ArrayDeque<>();
        char[] chars = str.toCharArray();
        for (char ch : chars) {
            if (ch != ']') {
                stack.push(String.valueOf(ch));
            } else {
                StringBuilder temp = new StringBuilder();
                String top;
                while (!(top = stack.pop()).equals("[")) {
                    temp.insert(0, top);
                }
                String resolving = resolving(temp.toString());
                stack.push(resolving);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.removeLast());
        }
        return res.toString();
    }

    String resolving(String str) {
        String[] split = str.split("\\|");
        int n = Integer.parseInt(split[0]);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(split[1]);
        }
        return sb.toString();
    }


    // 单调队列性质：如果前面的数都小于当前数，则前面的数都会被抛弃
    public int[] findBuilding(int[] heights) {
        // write code here
        int n = heights.length;
        int[] res = new int[n];
        Arrays.fill(res, 1);
        Deque<Integer> leftDeque = new ArrayDeque<>();
        Deque<Integer> rightDeque = new ArrayDeque<>();
        for (int i = 0; i < n - 1; i++) {
            // 看情况 removeFirst() 或者 removeLast()
            while (!leftDeque.isEmpty() && heights[i] >= leftDeque.peekFirst()) {
                leftDeque.removeFirst();
            }
            leftDeque.addFirst(heights[i]);
            res[i + 1] += leftDeque.size();
        }
        for (int i = n - 1; i > 0; i--) {
            while (!rightDeque.isEmpty() && heights[i] >= rightDeque.peekFirst()) {
                rightDeque.removeFirst();
            }
            rightDeque.addFirst(heights[i]);
            res[i - 1] += rightDeque.size();
        }
        return res;
    }
}
