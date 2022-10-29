package test;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test13 {

    /**
     * 完全背包问题
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int cloumn = coins.length;
        int[][] dp = new int[cloumn + 1][amount + 1];// 行表示容量，列表示物品
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = Integer.MAX_VALUE - 1000;
            }
        }
        // dp[i][j] = max(dp[i−1][j], dp[i][j−w[i]]+v[i]) // j >= w[i]
        for (int i = 1; i <= cloumn; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j >= coins[i - 1]) dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                else dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[cloumn][amount] == (Integer.MAX_VALUE - 1000) ? -1 : dp[cloumn][amount];
    }

    Map<Integer, Integer> map = new HashMap<>();

    public int combinationSum4(int[] nums, int target) {
        return dfsbeibao(nums, target);
    }

    /**
     * 使用减枝来解决完全背包问题
     */
    private int dfsbeibao(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }
        if (map.containsKey(target)) {
            return map.get(target);
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += dfsbeibao(nums, target - nums[i]);
        }
        // 保存的是值为target的时候有多少种排列组合
        map.put(target, res);
        return res;
    }


    public static int f(int n) {
        return n > 0 ? n * f(n - 1) : 2;
    }

    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (r < s.length()) {
            char c = s.charAt(r);
            if (set.contains(c)) {
                while (set.contains(c)) {
                    set.remove(s.charAt(l));
                    l++;
                }
            }
            set.add(s.charAt(r));
            r++;
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    List<List<Integer>> res;
    boolean[] marked;
    Deque<Integer> deque;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        marked = new boolean[nums.length];
        deque = new ArrayDeque<Integer>();
        dfs(nums, 0);
        return res;
    }

    private void dfs(int[] nums, int path) {
        if (path == nums.length) {
            res.add(new ArrayList<>(deque));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (marked[i]) {
                continue;
            }
            marked[i] = true;
            deque.addLast(nums[i]);
            dfs(nums, path + 1);
            marked[i] = false;
            deque.removeLast();
        }
    }

    public static void main(String[] args) {
        int count = coinChange(new int[]{1, 2, 5}, 11);
        System.out.println(count);
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        // 找到长度最小
        int min = Integer.MAX_VALUE;
        for (String str : strs) {
            min = Math.min(str.length(), min);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < min; i++) {
            for (int j = 0; j < strs.length - 1; j++) {
                if (strs[j].charAt(i) != strs[j + 1].charAt(i)) return sb.toString();
            }
            sb.append(strs[0].charAt(i));
        }
        return sb.toString();
    }

    public static int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int nums = 0;
        int idx = drinks.length - 1;
        for (int i = 0; i < staple.length; i++) {
            if (x - staple[i] < 0) break;
            int m = binarySearch(drinks, 0, idx, x - staple[i]);
            idx = m;
            nums = nums + (m + 1);
        }
        return nums;
    }

    // 二分查找，找到小于等于该数的最大数下标
    public static int binarySearch(int[] nums, int left, int right, int t) {
        int l = left;
        int r = right;
        int mid = 0;
        while (l <= r) {
            mid = (l + r) >>> 1;
            if (t > nums[mid]) {
                l = mid + 1;
            } else if (t < nums[mid]) {
                r = mid - 1;
            } else {
                // 需要判断后面一个数是否与前面一个数相同
                while (mid < right && nums[mid] <= t) {
                    mid++;
                }
                return mid;
            }
        }
        return nums[mid] > t ? mid - 1 : mid;
    }


    public static List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (i == 0) {
                continue;
            }
            if (valid(i)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 判断是否是自除数
     */
    public static boolean valid(int num) {
        int x = 10;
        int n = num % x;
        while (n > 0) {
            if (num % n != 0) return false;
            x *= 10;
            n = num / x;
        }
        return true;
    }

    /**
     * 需要把0空出来，也就是没有字符串的时候
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        // 不改变字符的相对位置
        int n1 = text1.length();
        int n2 = text2.length();
        // dp[0][0]表示text1==null text2==null !!!
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 1; i <= n1; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n2; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n1][n2];
    }
}
