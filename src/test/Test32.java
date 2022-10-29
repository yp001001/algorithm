package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Test32 {
    static int[][] goods = new int[][]{{2, 2}, {3, 2}, {1, 3}, {5, 1}, {4, 5}, {3, 2}};

    public static void main(String[] args) throws IOException {
        Test32 test32 = new Test32();
        boolean flag = test32.Game24Points(new int[]{100, 100, 100, 125});
        System.out.println(flag);
    }

    /**
     * 01背包问题（不拿也是从上，拿也是从上，因为数量唯一）
     *
     * @throws IOException
     */
    private static void beibao01() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int money = Integer.parseInt(s);
        // 01背包问题
        int row = goods.length;
        int[][] dp = new int[row + 1][money + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= money; j++) {
                if (goods[i - 1][1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - goods[i - 1][1]] + goods[i - 1][0]);
                }
            }
        }
        System.out.println(dp[row][money]);
    }

    /**
     * 01背包优化
     *
     * @throws IOException
     */
    private static void beibao01_2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int money = Integer.parseInt(s);
        int[] dp = new int[money + 1];
        for (int i = 0; i < goods.length; i++) {
            for (int j = money; j >= 0; j--) {
                if (goods[i][1] > j) dp[j] = dp[j];
                else dp[j] = Math.max(dp[j], dp[j - goods[i][1]] + goods[i][0]);
            }
        }
        System.out.println(dp[money]);
    }

    /**
     * 完全背包问题（可能从上，也可能从下，因为数量无限）
     *
     * @throws IOException
     */
    private static void wanquanbeibao() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int money = Integer.parseInt(s);
        int row = goods.length;
        int[][] dp = new int[row + 1][money + 1];
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= money; j++) {
                if (goods[i - 1][1] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - goods[i - 1][1]] + goods[i - 1][0]);
            }
        }
        System.out.println(dp[row][money]);
    }

    /**
     * 完全背包优化
     *
     * @throws IOException
     */
    private static void wanquanbeibao_2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int money = Integer.parseInt(s);
        int[] dp = new int[money + 1];
        for (int i = 0; i < goods.length; i++) {
            for (int j = 0; j <= money; j++) {
                if (goods[i][1] > j) dp[j] = dp[j];
                else dp[j] = Math.max(dp[j - goods[i][1]] + goods[i][0], dp[j]);
            }
        }
        System.out.println(dp[money]);
    }


    //    现在有n个物品，每一个物品都有一个价值，现在想将这些物品分给两个人，要求这两个人每一个人分到的物品的价值总和相同（个数可以不同，总价值相同即可）
    //    剩下的物品就需要扔掉，现在想知道最少需要扔多少价值的物品才能满足要求分给两个人。
    static int minGoods = Integer.MAX_VALUE;

    public void wangyi_01(int[] goods) {
        int sum = 0;
        for (int good : goods) {
            sum += good;
        }

        dfs(goods, 0, 0, sum, 0);
    }

    /**
     * 找到所有可能相等的排列，比较最少丢失的价值
     *
     * @param goods
     * @param n
     * @param m
     * @param sum
     * @param start
     */
    private void dfs(int[] goods, int n, int m, int sum, int start) {
        if (n == m) {
            minGoods = Math.min(minGoods, sum - n * 2);
        }
        if (start >= goods.length) {
            dfs(goods, n + goods[start], m, sum, start + 1); //  给n
            dfs(goods, n, m + goods[start], sum, start + 1); //  给m
            dfs(goods, n, m, sum, start + 1);
        }
    }

    /**
     * 奇安信，三角形三边求和
     *
     * @throws IOException
     */
    public static void qianxin() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String i = scanner.next();
        String[] str = i.split(",");
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : str) {
            list.add(Integer.parseInt(s));
        }

        int n1 = list.get(0) + list.get(1) + list.get(2) + list.get(3);
        int n2 = list.get(3) + list.get(4) + list.get(5) + list.get(6);
        int n3 = list.get(6) + list.get(7) + list.get(8) + list.get(0);

        if (n1 == n2 && n2 == n3) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }

    boolean flag = false;

    public boolean Game24Points(int[] arr) {
        // write code here
        dfs(arr, 1, arr[0]);
        return flag;
    }

    private void dfs(int[] arr, int start, int sum) {
        if (start > arr.length) return;
        if (start == arr.length && sum == 24) {
            flag = true;
            return;
        }
        if (start == arr.length) return;
        dfs(arr, start + 1, sum + arr[start]);
        dfs(arr, start + 1, sum - arr[start]);
        dfs(arr, start + 1, sum * arr[start]);
        dfs(arr, start + 1, sum / arr[start]);
    }
}
