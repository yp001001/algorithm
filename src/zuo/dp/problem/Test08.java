package zuo.dp.problem;

import java.util.*;

public class Test08 {

    public static void main(String[] args) {
        Test08 test08 = new Test08();
        int[] result = test08.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(result));
    }

    //=====================================打开转盘锁================================

    public int openLock(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String deadend : deadends) {
            deads.add(deadend);
        }
        // 记录已经穷举过的密码
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        int step = 0;
        q.offer("0000");
        visited.add("0000");
        while (!q.isEmpty()) {
            int sz = q.size();
            // 将当前队列中所有节点向周围扩散
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                if (deads.contains(cur)) continue;
                if (cur.equals(target)) return step;
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    // 将 s[j] 向上拨动一次
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9') ch[j] = '0';
        else ch[j] += 1;
        return new String(ch);
    }

    // 将 s[i] 向下拨动⼀次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0') ch[j] = '9';
        else ch[j] -= 1;
        return new String(ch);
    }


    //=====================================最长回文子串================================

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][n - 1];
    }

    //=====================================最长回文子串================================

    int[] memo;

    public int jump(int[] nums) {
        int n = nums.length;
        // 备忘录都初始化为n，相当于INT_MAX
        // 因为从0跳到n-1最多n-1步
        memo = new int[n];
        Arrays.fill(memo, n);
        return dp(nums, 0);
    }

    int dp(int[] nums, int p) {
        int n = nums.length;
        // base case
        if (p >= n - 1) {
            return 0;
        }
        if (memo[p] != n) {
            return memo[p];
        }
        int steps = nums[p];
        // 可以选择跳1步，2步...
        for (int i = 1; i <= steps; i++) {
            // 穷举每一个选择
            int subProblem = dp(nums, p + i);
            memo[p] = Math.min(memo[p], subProblem + 1);
        }
        return memo[p];

    }


    //==========================================每日温度====================

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = temperatures.length;
        int[] result = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!deque.isEmpty() && temperatures[deque.peekLast()] <= temperatures[i]) {
                deque.removeLast();
            }
            if (!deque.isEmpty()) {
                result[i] = deque.peekLast() - i;
            } else {
                result[i] = 0;
            }
            deque.addLast(i);
        }

        return result;
    }

    //==========================================下一个更大元素II====================

    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ret = new int[n];
        Arrays.fill(ret, -1);
        Deque<Integer> stack = new LinkedList<>();
        for (int i = n * 2 - 1; i >= 0; i++) {
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }
            if (!stack.isEmpty()) ret[i % n] = stack.peek();
            stack.push(nums[i % n]);
        }
        return ret;
    }


}
