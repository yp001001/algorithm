package test2;

import test.tree.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Test13 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] split = sc.nextLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int l = Integer.parseInt(split[1]);
        int[][] stages = new int[n][2];
        for (int i = 0; i < n; i++) {
            split = sc.nextLine().split(" ");
            stages[i][0] = Integer.parseInt(split[0]);
            stages[i][1] = Integer.parseInt(split[1]);
        }
        // 进行排序
        Arrays.sort(stages, (o1, o2) -> o1[0] - o2[0] == 0 ? o1[1] - o2[1] : o1[0] - o2[0]);
        int index = 0;
        int count = 0;
        int pre = 0; // 右边界
        while (pre < l) {
            if (stages[index][0] > pre) {
                System.out.println(-1);
                return;
            }
            int max = 0;
            while (index < n && stages[index][0] < pre) {
                max = Math.max(max, stages[index][1]);
                index++;
            }
            count++;
            pre = max;
            if (pre >= l) {
                System.out.println(count);
            }
            if (index >= n) {
                System.out.println(-1);
                return;
            }
        }
    }


    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] worker = new int[n];
        int[] sport = new int[n];
        String[] s = sc.nextLine().split(" ");
        for (int j = 0; j < n; j++) {
            worker[j] = Integer.parseInt(s[j]);
        }
        s = sc.nextLine().split(" ");
        for (int j = 0; j < n; j++) {
            sport[j] = Integer.parseInt(s[j]);
        }

        long[][] dp = new long[n + 1][3];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= n; i++) {
            if (worker[i - 1] == 1) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]);
            }
            if (sport[i - 1] == 1) {
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]);
            }
            dp[i][2] = Math.min(dp[i - 1][2], Math.min(dp[i - 1][0], dp[i - 1][1])) + 1;
        }
        System.out.println(Math.min(dp[n][2], Math.min(dp[n][0], dp[n][1])));
    }
}
