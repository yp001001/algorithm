package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * (7) SELECT
 * <p>
 * (8) DISTINCT <select_list>
 * <p>
 * (1) FROM <left_table>
 * <p>
 * (3) <join_type> JOIN <right_table>
 * <p>
 * (2) ON <join_condition>
 * <p>
 * (4) WHERE <where_condition>
 * <p>
 * (5) GROUP BY <group_by_list>
 * <p>
 * (6) HAVING <having_condition>
 * <p>
 * (9) ORDER BY <order_by_condition>
 * <p>
 * (10) LIMIT <limit_number>
 */
public class Test6 {
    static int count = 0;

    public static void main(String[] args) throws IOException {

    }


    /**
     * 01背包
     *
     * @param goods
     * @param capacity
     * @return
     */
    public static int beibao02(int[][] goods, int capacity) {
        int[][] dp = new int[goods.length + 1][capacity + 1];
        int row = goods.length; // 行
        int column = capacity;  // 列
        for (int i = 0; i <= row; i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < column; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= column; j++) {
                // 容量大于当前物品重量
                if (j >= goods[i - 1][1]) {
                    // goods[i][0]表示价值，goods[i][1]表示重量
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - goods[i - 1][1]] + goods[i - 1][0]);
                    for (int x = j + 1; x <= column; x++) {
                        dp[i][j] = dp[i][j - 1];
                    }
                    continue;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[goods.length][capacity];
    }


    /**
     * 完全背包问题
     *
     * @param goods
     * @param capacity
     * @return
     */
    public static int beibao01(int[][] goods, int capacity) {
        // 状态转移方程为 dp[i][j] = Max(dp[i-1][j],dp[i-1][j-w(i)]+v(i) )
        int[][] dp = new int[goods.length + 1][capacity + 1];
        // 初始化状态
        for (int i = 0; i <= capacity; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= goods.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= goods.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                // 容量大于当前物品重量
                if (j >= goods[i - 1][1]) {
                    // goods[i][0]表示价值，goods[i][1]表示重量
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - goods[i - 1][1]] + goods[i - 1][0]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[goods.length][capacity];
    }


    /**
     * 求获取最大赞数以及最小本数
     *
     * @throws IOException
     */
    private static void xiaohongshu2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String c = br.readLine();
        int count = Integer.parseInt(c);
        String s = br.readLine();
        String[] split = s.split(" ");
        int[] nums = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        if (count <= 0) {
            System.out.println(0 + " " + 0);
        } else if (count == 1) {
            System.out.println(nums[0] + " " + 1);
        } else {
            int[] dp = new int[count + 1];
            int[] cdp = new int[count + 1];
            dp[1] = Math.max(nums[0], nums[1]);
            cdp[1] = 1;
            for (int i = 2; i < count; i++) {
                if (dp[i - 1] > dp[i - 2] + nums[i]) {
                    dp[i] = dp[i - 1];
                    cdp[i] = cdp[i - 1];
                } else {
                    dp[i] = dp[i - 2] + nums[i];
                    cdp[i] = cdp[i - 2] + 1;
                }
            }
            System.out.println(dp[count - 1] + " " + cdp[count - 1]);
        }
    }


    /**
     * 小红书全排列，超时！！！
     *
     * @throws IOException
     */
    private static void xiaohongshuquanpailie() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int m;
        int x = 0;
        while (s.charAt(x) >= '0' && s.charAt(x) <= '9' && s.length() > x) {
            x++;
        }
        String n = s.substring(0, x);
        m = Integer.parseInt(n);
        int[] money = new int[1];
        money[0] = m;
        int[] res = res(s);    // 每件商品的单价
        dfs(res, money, 0);
        System.out.println(count);
    }

    public static void dfs(int[] res, int[] money, int path) {
        if (path >= res.length || money[0] < 0) {
            return;
        }
        if (money[0] == 0) {
            count++;
            return;
        }
        // 回溯算法
        money[0] -= res[path];
        dfs(res, money, path);
        money[0] += res[path];
        dfs(res, money, path + 1);
    }

    public static int[] res(String s) {
        int len = s.length();
        int start = 0;
        int end = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == '[') {
                start = i;
            }
            if (s.charAt(i) == ']') {
                end = i;
                break;
            }
        }
        s = s.substring(start + 1, end);
        String[] sp = s.split(",");
        int[] res = new int[sp.length];
        for (int i = 0; i < sp.length; i++) {
            res[i] = Integer.parseInt(sp[i]);
        }
        return res;
    }

    /**
     * @param head
     * @return
     */

    public ListNode sortList(ListNode head) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((l1, l2) -> {
            return l1.val - l2.val;
        });
        ListNode t = head;
        while (t != null) {
            queue.add(t);
            t = t.next;
        }
        ListNode res = new ListNode();
        ListNode temp = res;
        while (!queue.isEmpty()) {
            ListNode n = queue.poll();
            temp.next = n;
            temp = temp.next;
        }
        return res.next;
    }

    public boolean isPalindrome(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode n = head;
        while (n != null) {
            list.add(n);
            n = n.next;
        }
        for (int i = 0; i < list.size() / 2; i++) {
            if (list.get(i).val != list.get(list.size() - i).val) {
                return false;
            }
        }
        return true;
    }
}
