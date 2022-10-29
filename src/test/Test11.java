package test;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 *
 * @Lazy
 * ObjectFactory
 * Provider
 * @Scope(proxyMode = ScopedProxyMode) 配置类或组件扫描才能生效
 *
 * UNION UNIONALL 区别
 * <p>
 * <p>
 * <p>
 * // timed变量用于判断是否需要进行超时控制。
 * // allowCoreThreadTimeOut默认是false，也就是核心线程不允许进行超时；
 * // wc > corePoolSize，表示当前线程池中的线程数量大于核心线程数量；
 * // 对于超过核心线程数量的这些线程，需要进行超时控制
 * boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
 * <p>
 * Runnable r = timed ?
 * workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) :
 * workQueue.take();
 */
public class Test11 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(Test12.A.mm);
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        int len = wordDict.size();
        // 将数组中的数据保存到set集合中
        Set<String> set = new HashSet<>(wordDict);
        boolean[] flag = new boolean[len + 1];
        flag[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (flag[i - 1] && set.contains(s.substring(j, i))) {
                    flag[i] = true;
                }
            }
        }
        return flag[len];
    }

    public boolean hasAlternatingBits(int n) {
        if (n == 0 || n <= 2) {
            return true;
        }
        int pre = n % 2;
        n /= 2;
        while (n > 0) {
            int x = n % 2;
            if (pre == x) {
                return false;
            }
            n /= 2;
        }
        return true;
    }


    /**
     * 买卖股票的最佳时机II
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][2];
        dp[0][0] = 0;           //  表示不持有这个股票
        dp[0][1] = -prices[0];  //  表示持有这个股票
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }


    /**
     * 买卖股票的最佳时机含冷冻期
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][3];
        dp[0][0] = -prices[0];           //  持有一只股票
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);        // 持有股票
            dp[i][1] = dp[i - 1][0] + prices[i];                                // 不持有股票，处于冷冻期
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);                        // 不持有股票，不处于冷冻期
        }
        return Math.max(dp[len - 1][2], Math.max(dp[len - 1][0], dp[len - 1][1]));
    }

}
