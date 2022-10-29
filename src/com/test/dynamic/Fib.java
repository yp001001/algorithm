package com.test.dynamic;

/*
动态规划相关问题


java编译过程：
  1. Javac将Java源代码编译成Java字节码，也就是JVM能够识别的二进制代码，从表面看是.java文件转换成.class文件
     而实际上是将Java源代码转化成一连串二进制数据（只有JVM能够识别）
  2. JVM在转换成机器语言

  （机器语言 -> 汇编语言 -> 高级语言）
java为何是半解释型半编译型语言？
  1. 编译型语言，是将代码通过编译器编译成机器指令码的文件，然后再让计算机运行
  2. 解释型语言，就是通过解释器一行一行的将代码翻译成机器指令码执行

  Java：首先将源文件编译成字节码文件（.class文件），然后通过JVM虚拟机解释执行
      （（Hotspot）JVM存在JIT即时编译器，能够捕获程序中的热点代码，编译成机器码指令缓存起来存储在方法区）


  Redis只是在处理用户请求的时候是单线程的
 */
public class Fib {


    public int fib(int n) {
        if (n == 1) return 1;
        if (n == 0) return 0;
        return fib(n - 1) + fib(n - 2);
    }

    public int fib2(int n) {
        if (n < 2) return n;
        int p = 0, q = 0, sum = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = sum;
            sum = p + q;
        }
        return sum;
    }


    // 第 N 个泰波那契数
    // T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
    public int tribonacci(int n) {
        if (n < 2) return n;
        if (n == 2) return 1;
        int p = 0, q = 1, l = 1, sum = 2;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = l;
            l = sum;
            sum = p + q + l;
        }
        return sum;
    }

    /*
    爬楼梯
     */
    public int climbStairs(int n) {
        if (n < 3) {
            return n;
        }
        int p = 0, q = 1, sum = 2;
        for (int i = 3; i <= n; i++) {
            p = q;
            q = sum;
            sum = p + q;
        }
        return sum;
    }

    /*
    使用最小花费爬楼梯
     */
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] dp = new int[length + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[length];
    }

    /*
    打家劫舍
     */
    public int rob(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[length - 1];
    }

    /*
    打家劫舍2.0
     */
    public int rob2(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            return nums[0];
        }
        if (length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(robRange(nums, 0, length - 2), robRange(nums, 1, length - 1));
    }

    public int robRange(int[] nums, int start, int end) {
        int dp[] = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start + 1], nums[start]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }

    /*
    740. 删除并获得点数
    （找出最大数，将nums的值作为dp[]的下标）
     */
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max = 0;
        // 得到最大值
        for (int num : nums) {
            max = Math.max(num, max);
        }
        int[] dp = new int[max + 1];
        // 相当于打家劫舍
        for (int num : nums) {
            // 此处必须是 +=，例如 [2,3,3,3,4]，此时不是 +=的话 arr[3]=3,是 += 的话 arr[3]=9
            dp[num] += num;
        }
        // 此时就和打家劫舍的解决方案一致
        return robRange2(dp);
    }

    private int robRange2(int[] dp) {
        int length = dp.length;
        int[] arr = new int[length];
        // arr[length-1]  or arr[length-2]+dp[length-1]
        arr[0] = dp[0];
        arr[1] = Math.max(dp[0], dp[1]);
        for (int i = 2; i < length; i++) {
            arr[i] = Math.max(arr[i - 2] + dp[i], arr[i - 1]);
        }
        return arr[length - 1];
    }

    //    55. 跳跃游戏
    public boolean canJump(int[] nums) {
        return false;
    }
}
