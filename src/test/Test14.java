package test;

import java.util.*;

/**
 * 背包问题
 */
public class Test14 {
    Set<String> res;
    boolean[] marked;

    public String[] permutation(String s) {
        res = new HashSet<>();
        char[] chars = s.toCharArray();
        marked = new boolean[s.length()];
        StringBuilder sb = new StringBuilder();
        dfs(chars, 0, marked, sb);
        String[] strs = new String[res.size()];
        Iterator itr = res.iterator();
        int idx = 0;
        while (itr.hasNext()) {
            strs[idx++] = (String) itr.next();
        }
        return strs;
    }

    private void dfs(char[] chars, int path, boolean[] marked, StringBuilder sb) {
        if (sb.length() == chars.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (marked[i]) continue;
            sb.append(chars[i]);
            marked[i] = true;
            dfs(chars, i + 1, marked, sb);
            sb.deleteCharAt(sb.length() - 1);
            marked[i] = false;
        }
    }


    public static void main(String[] args) {

        Test14 test14 = new Test14();
        String[] strs = test14.permutation("abc");
        for (String str : strs) {
            System.out.println(str);
        }
    }

    /**
     * 零钱兑换
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        long[] dp = new long[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (j < coins[i]) dp[j] = dp[j];
                else dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);     // 有溢出风险
            }
        }
        return (int) (dp[amount] >= Integer.MAX_VALUE ? -1 : dp[amount]);
    }


    /**
     * goods[0] 表示物品的重量，goods[1]表示物品的价值
     * ①：dp[i][j] = dp[i-1][j] / Max(dp[i-1][j-w[i]]+v[i],dp[i-1][j]]
     * ②：dp[i] = Max(dp[i],dp[i-w[i]]+v[i])
     *
     * @param goods
     * @param capacity
     */
    public static int beiBao01(int[][] goods, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < goods.length; i++) {
            for (int j = capacity; j >= 0; j--) {
                if (j < goods[i][0]) dp[j] = dp[j];
                else dp[j] = Math.max(dp[j], dp[j - goods[i][0]] + goods[i][1]);
            }
        }
        return dp[capacity];
    }

    /**
     * 完全背包
     *
     * @param goods
     * @param capacity
     * @return
     */
    public static int wanquanBeibao(int[][] goods, int capacity) {
        int[] dp = new int[capacity + 1];
        for (int i = 0; i < goods.length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (j < goods[i][0]) dp[j] = dp[j]; // 从上面来
                else dp[j] = Math.max(dp[j], dp[j - goods[i][0]] + goods[i][1]);
            }
        }
        return dp[capacity];
    }

}
