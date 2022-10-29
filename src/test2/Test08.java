package test2;

import java.util.*;

public class Test08 {

    public static void main(String[] args) {
        new Test08().romanToInt("MCMXCIV");
    }

    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        int num = getNext(n);
        while (!set.contains(num)) {
            if (num == 1) {
                return true;
            }
            // 将计算过的值保存到set集合中
            set.add(num);
            num = getNext(num);
        }
        return false;
    }

    /**
     * 获取平方相加之后的数
     *
     * @param n
     * @return
     */
    private static int getNext(int n) {
        int target = 0;
        while (n > 0) {
            int x = n % 10;
            n /= 10;
            target += x * x;
        }
        return target;
    }


    /**
     * 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int lmax = 0;
        int rmax = 0;
        int n = height.length;
        int[] lHeight = new int[n];
        int[] rHeight = new int[n];
        // 找到该点左边的最大高度
        for (int i = 0; i < n; i++) {
            lmax = Math.max(lmax, height[i]);
            lHeight[i] = lmax;
        }
        // 找到该点右边的最大高度
        for (int i = n - 1; i >= 0; i--) {
            rmax = Math.max(rmax, height[i]);
            rHeight[i] = rmax;
        }
        int res = 0;
        for (int i = 1; i < n - 1; i++) {
            res += Math.min(lHeight[i], rHeight[i]) - height[i];
        }
        return res;
    }

    /***
     * 解码方法
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0') return 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 当前数不为0
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // 能与前面一个数组成字母
            if (s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    /**
     * 约瑟夫环
     *
     * @param n
     * @param k
     * @return
     */
    public int findTheWinner_1(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }
        while (queue.size() > 1) {
            for (int i = 1; i < k; i++) {
                queue.offer(queue.poll());
            }
            queue.poll();
        }
        return queue.peek();
    }

    public int findTheWinner(int n, int k) {
        if (n == 1) {
            return 1;
        }
        return (k + findTheWinner(n - 1, k) - 1) % n + 1;
    }


    /**
     * 二叉树的不同组合
     * G(n)=∑F(i,n)
     * F(i,n)=G(i−1)⋅G(n−i)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }


    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int sum = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char cur = s.charAt(i);
            char next = s.charAt(i + 1);
            if (map.get(cur) < map.get(next)) sum -= map.get(cur);
            else sum += map.get(cur);
        }
        sum += map.get(s.charAt(s.length() - 1));
        return sum;
    }


    public int maxProfit(int[] prices) {
        int money = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            money = Math.max(prices[i] - min, money);
        }
        return money;
    }
}
