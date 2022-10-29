package topic.package06;

import java.util.ArrayDeque;
import java.util.Stack;

// https://leetcode.cn/problems/largest-component-size-by-common-factor/
public class Problem04 {

//    public int largestComponentSize(int[] nums) {
//
//    }


    // 求最大公约数，a,b均是大于0的正数
    public static int gcb(int a, int b) {
        return b == 0 ? a : gcb(b, a % b);
    }


    // 最大元素范围再left-right
    public static int numSubarrayBoundedMax(int[] arr, int L, int R) {
        if (arr == null || arr.length == 0) return 0;
        // arr的所有子数组中，根据子数组最大值情况，可分为三类：(..., L-1], [L, R], [R+1, ...)
        // 范围在[L, R]的个数 == count(..., R] - count(..., L-1]
        return countOfFloor(arr, R) - countOfFloor(arr, L - 1);
    }

    // 返回数组arr中，最大值小于等于k的子数组的个数
    // 最初始的DP解：真实申请dp数组，不做空间优化
    public static int countOfFloor1(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return 0;
        // dp[i]：必须以i位置结尾的子数组arr[... i]，满足最大值 <= k的子数组个数
        int[] dp = new int[n];
        dp[0] = arr[0] <= k ? 1 : 0;
        int count = dp[0];
        for (int i = 1; i < n; i++) {
            // 求必须以i结尾的子数组满足条件的个数dp[i]
            // 1. 如果当前i位置元素值小于等于k：
            // 1）则至少可以形成1个符合条件的子数组（arr[i]）
            // 2）然后再看前面以i-1位置结尾有多少个符合条件的子数组，对于前面的每个符合条件的子数组，arr[i]都可以追加到其后面，形成的子数组同样符合条件，dp[i-1]个
            // 总共获得dp[i-1]+1个
            // 2. 如果当前i位置元素值大于k，则必须以i结尾没有符合条件的子数组
            dp[i] = arr[i] <= k ? dp[i - 1] + 1 : 0;
            count += dp[i];
        }
        return count;
    }

    // 返回数组arr中，最大值小于等于k的子数组的个数
    // 空间优化后的DP解
    public static int countOfFloor(int[] arr, int k) {
        int n = arr.length;
        if (n == 0) return 0;
        // dp[i]：必须以i位置结尾的子数组arr[... i]，满足最大值 <= k的子数组个数
        int dp = arr[0] <= k ? 1 : 0;
        int count = dp;
        for (int i = 1; i < n; i++) {
            dp = arr[i] <= k ? dp + 1 : 0;
            count += dp;
        }
        return count;
    }


    public static int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int sz1 = s1.length;
        int sz2 = s2.length;
        for (int i = 0; i < Math.min(sz1, sz2); i++) {
            int n1 = Integer.parseInt(s1[i]);
            int n2 = Integer.parseInt(s2[i]);
            if (n1 > n2) return 1;
            else if (n1 < n2) return -1;
        }
        if (sz1 > sz2) {
            for (int i = sz2; i < sz1; i++) {
                if (Integer.parseInt(s1[i]) != 0) return 1;
            }
        } else if (sz1 < sz2) {
            for (int i = sz1; i < sz2; i++) {
                if (Integer.parseInt(s2[i]) != 0) return -1;
            }
        }
        return 0;
    }


    public static String Paired69(String S) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            if (c == '6') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    sb.insert(0, '6');
                } else {
                    stack.pop();
                }
            }
            sb.append(c);
        }
        while (!stack.isEmpty()) {
            stack.pop();
            sb.append('9');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int a = 3, b = 5;
        System.out.println("a = " + a + " b = " + b);
        if (a != b) {
            a ^= b;
            b ^= a;
            a ^= b;
        }
        System.out.println("a = " + a + " b = " + b);
    }
}