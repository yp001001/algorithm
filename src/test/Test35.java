package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Test35 {
    public static void main(String[] args) {
        interview58_2();
    }

    /**
     * 前后都遍历一次，取最大值
     * **********重点*********
     */
    private static void interview58_2() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        //
        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1])
                dp[i] = dp[i - 1] + 1;
        }

        int[] dp2 = new int[n];
        Arrays.fill(dp2, 1);
        //
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1])
                dp2[i] = dp2[i + 1] + 1;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += Math.max(dp[i], dp2[i]);
        }
        System.out.println(res);
    }


    /**
     * 36进制加法
     *
     * @param a
     * @param b
     * @return
     */
    static String add(String a, String b) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        Map<Integer, Character> map2 = new HashMap<>();
        char c = '0';
        for (int i = 0; i < 10; i++) {
            map.put(c, i);
            map2.put(i, c);
            c++;
        }
        c = 'a';
        for (int i = 10; i < 36; i++) {
            map.put(c, i);
            map2.put(i, c);
            c++;
        }
        int n = a.length() - 1;
        int m = b.length() - 1;
        int z = 0;  // 记录进位
        while (n >= 0 || m >= 0) {
            char p1 = n >= 0 ? a.charAt(n--) : '0';
            char p2 = m >= 0 ? b.charAt(m--) : '0';
            int n1 = map.get(p1);
            int n2 = map.get(p2);
            sb.append(map2.get((n1 + n2 + z) % 36));
            z = (n1 + n2) / 36;
        }
        if (z > 0) {
            sb.append(z);
        }
        return sb.reverse().toString();
    }


    private static void interview58() {
        Scanner sc = new Scanner(System.in);
        int row = Integer.parseInt(sc.nextLine());
        int column = Integer.parseInt(sc.nextLine());
        int[][] nums = new int[row][column];
        for (int i = 0; i < row; i++) {
            String next = sc.nextLine();
            String[] sp = next.split(" ");
            for (int j = 0; j < column; j++) {
                nums[i][j] = Integer.parseInt(sp[j]);
            }
        }
        int[][] dp = new int[row][column];
        dp[0][0] = nums[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + nums[i][0];
        }
        for (int i = 1; i < column; i++) {
            dp[0][i] = dp[0][i - 1] + nums[0][i];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < column; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + nums[i][j];
            }
        }
        System.out.println(dp[row - 1][column - 1]);
    }
}
