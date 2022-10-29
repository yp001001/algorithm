package zuo.dp.dp;

import java.util.Scanner;

public class Problem2 {

    //==================================================最长特殊序列II============================

    // 判断s1是否是s2的子序列
    public boolean LUSlength(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) return false;
        int l1 = 0, l2 = 0;
        while (l2 < n2) {
            if (s1.charAt(l1) == s2.charAt(l2)) {
                l1++;
                l2++;
            } else {
                l2++;
            }
            if (l1 == n1) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double speed = 0;
        double sum = 0;
        int[] v = new int[n];
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = sc.nextInt();
            speed += v[i];
        }
        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
            sum += v[i] * t[i];
        }
        System.out.println(String.format("%.4f", (sum / speed)));
    }
}
