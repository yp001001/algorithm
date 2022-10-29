package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Test9 {
    public static void main(String[] args) {
        int x = maxSubarraySumCircular(new int[]{5, -3, 5});
        System.out.println(x);
    }

    public static int maxSubarraySumCircular(int[] nums) {
        // 两种情况
        // 1. 没有回头,max
        // 2. 回头,sum-min
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0, pre = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (pre < 0) {
                pre = nums[i];
            } else {
                pre = nums[i] + pre;
            }
            max = Math.max(pre, max);
        }

        pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if (pre <= 0) {
                pre = nums[i] + pre;
            } else {
                pre = nums[i];
            }
            min = Math.min(pre, min);
        }
        System.out.println("min:" + min);
        System.out.println("max:" + max);
        return Math.max(sum - min, max);
    }

    public static int maxProduct(int[] nums) {
        int pre = 1;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            pre = Math.max(num * pre, pre);
            max = Math.max(max, pre);
        }
        return max;
    }

    /**
     * 不能使用dp，无法知晓使用 f(x-1)+nums[x] 还是使用 nums[x]
     * </p>
     * (错误)xxxxxxxxxxxxx
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            if (dp[i - 1] + nums[i] > dp[i - 1] && dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }


    /**
     * *未完成**
     * <p>
     * 小美喜欢7的倍数。桌面上有一些卡片，每张卡片上都印有一个数字，小美想从中挑选一些卡片，使得卡片上的数字之和最大，
     * 由于小美很喜欢7的倍数，她同时还希望挑选出的卡片的数字之和是7的倍数，请问她能挑选出的最大数字之和是多少？（注意，小美也可以一张卡片都不挑选）
     * 第一行是一个正整数n，表示桌上有n张写有数字的卡片。
     * 第二行有n个空格隔开的整数a1,a2,…,an，其中ai表示桌上第i张卡片上所写的数字。
     * 1<=n<=50000, |ai|<=3000
     * 一行一个整数，表示小美能挑选出的最大数字之和（且和为7的倍数）
     */
    public static void meituan01() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String c = br.readLine();
        String s = br.readLine();
        int count = Integer.parseInt(c);
        String[] split = s.split(" ");
        int[] nums = new int[count];
        for (int i = 0; i < count; i++) {
            nums[i] = Integer.parseInt(split[i]);
        }
        int res = 0;
        // 一行至多count个元素，和为7，则至多7行
        int[][] dp = new int[7][count];

    }


    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 找到大于nums[i]-t的最小值
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i > k) {
                set.remove((long) nums[k - i]);
            }
        }
        return false;
    }


    public int majorityElement(int[] nums) {
        // 快排
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 0; i < len; i++) {
            int res = map.get(nums[i]);
            if (res > (len / 2)) {
                return res;
            }
        }
        return -1;
    }

    public void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        // 以最左边的数为基准数
        int t = nums[left];
        int l = left;
        int r = right;
        while (l < r) {
            while (nums[r] >= t && l < r) {
                r--;
            }
            while (nums[l] <= t && l < r) {
                l++;
            }
            if (l < r) {
                swap(nums, l, r);
            }
        }
        // 交换基准数
        swap(nums, left, r);
        quickSort(nums, left, r - 1);
        quickSort(nums, r + 1, right);
    }

    public void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }


    private static void StringTest() {
        /**
         * final    A,B类型数据在编译器就确定了
         * s2+s3    s2和s3虽然是常量，但是没有马上被赋值，在运算出s2+s3治安，是一个变数，所以不能在编译期确定，只能在运行时确定，所以在运行时创建
         */
        final String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = "hello" + "world";
        System.out.println(s1 + s2 == s3); //true
        System.out.println(s1 + s2 == s4);  //false
        System.out.println(s1 + "world" == s4);  //false
    }
}
