package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test31 {

    public static void main(String[] args) throws IOException {
        Test31 test31 = new Test31();
        test31.jinsan_01_2();
    }

    /**
     * 最长递增子序列
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        // dp[i] = dp[j] + 1    j<i，且dp[i] > dp[j]
        int size = nums.length;
        int[] dp = new int[size];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < size; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 翻转字符
     *
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        int size = s.length();
        int[] dp = new int[size];
        int oneCount = s.charAt(0) == '1' ? 1 : 0;
        dp[0] = 0;
        for (int i = 1; i < size; i++) {
            if (s.charAt(i) == '1') {
                dp[i] = dp[i - 1];
                oneCount++;
            } else {
                dp[i] = Math.min(oneCount, dp[i - 1] + 1);
            }
        }
        return dp[size - 1];
    }

    /**
     * 粉刷房子
     *
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int size = costs.length;
        int[][] dp = new int[size][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];
        for (int i = 1; i < costs.length; i++) {
            // 吃哪边的就需要变成哪边
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + costs[i][2];
        }
        return Math.min(dp[size - 1][0], Math.min(dp[size - 1][1], dp[size - 1][2]));
    }


    /**
     * 复原 IP
     *
     * @param s
     * @return
     */
    List<String> res;

    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<>();
        if (s.length() > 12) return res;
        List<String> segment = new ArrayList<>();
        dfs(s, 0, segment);
        return res;
    }

    private void dfs(String s, int start, List<String> segment) {
        if (start == s.length()) {
            if (segment.size() == 4) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < segment.size(); i++) {
                    sb.append(segment.get(i));
                    if (i < 3) {
                        sb.append(".");
                    }
                }
                res.add(sb.toString());
            }
            return;
        }
        if (segment.size() >= 4) return;  // 如果size大于等于4，我们直接return
        for (int i = start; i < s.length() && i < start + 3; i++) {
            String str = s.substring(start, i + 1);
            if (str.charAt(0) == '0' && str.length() > 1) {
                break;
            }
            int num = Integer.parseInt(str);
            if (num >= 0 && num <= 255) {
                segment.add(str);
                dfs(s, i + 1, segment);
                segment.remove(segment.size() - 1);
            }
        }
    }


    //    把m个同样的足球放进n个同样的篮子里，允许有的篮子为空，问共有几种分法？
    //    例如：3, 2, 1和2, 1, 3是同一种分法。
    int count = 0;


    /**
     * 方式一： 使用递归
     **/
    public void jinsan_01() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String[] split = s.split(" ");
        int n = Integer.parseInt(split[0]); // 篮子数量
        int m = Integer.parseInt(split[1]); // 足球数量
        int solution = solution(n, m);
        System.out.println(solution);
    }

    /**
     * 使用递归
     */
    private int solution(int n, int m) {
        if (n == 1 || m == 0) {
            return 1;
        }
        if (n > m) {
            return solution(m, m);
        } else {
            // 有一个篮子不放 + 每个篮子都放一个
            return solution(n, m - n) + solution(n - 1, m);
        }
    }

    /**
     * 使用动态规划
     *
     * @throws IOException
     */
    public void jinsan_01_2() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String[] split = s.split(" ");
        int n = Integer.parseInt(split[0]); // 篮子数量
        int m = Integer.parseInt(split[1]); // 足球数量
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;   // 足球个数为0，表示一种情况
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (i > j) {
                    dp[i][j] = dp[j][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - i];
                }
            }
        }
        System.out.println(dp[n][m]);
    }


    /**
     * ===========================================================================================================
     */


    //    一台新机器，第4年可以生产一台机器，以后每一年生产1台。 生产出来的新机器第4年又可以生产一台机器，后续每年可以生产1台。
    //    现在有一台新机器，求第n年总共有多少台机器。 计算结果对10^9+7取模。

    /**
     * 第 i 年增长的量 = 第i-3年数量 +上一年的量 = 增长量 + 上一年的量
     *
     * @throws IOException
     */
    public void jinsan_02() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int years = Integer.parseInt(s);
        if (years < 4) {
            System.out.println(1);
        } else {
            int[] sum = new int[years + 1];
            sum[1] = 1;
            sum[2] = 1;
            sum[3] = 1;
            for (int i = 4; i <= years; i++) {
                sum[i] = (sum[i - 3] + sum[i - 1]) % 1000000007;
            }
            System.out.println(sum[years]);
        }
    }

}
