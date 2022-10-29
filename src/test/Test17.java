package test;

import java.util.*;

public class Test17 {

    public static void main(String[] args) {
        Test17 test17 = new Test17();
        List<List<String>> res = test17.partition("aab");
        for (List<String> re : res) {
            System.out.println(re);
        }
    }

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        char[] charArray = s.toCharArray();

        // 枚举所有长度严格大于1的字串，charArray[i...j]
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && isValid(charArray, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    private boolean isValid(char[] charArray, int i, int j) {
        for (int m = i, n = j; m < n; m++, n--) {
            if (charArray[m] != charArray[n]) return false;
        }
        return true;
    }


    /**
     * 分割子文字字符串
     *
     * @param s
     * @return
     */
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> partition(String s) {
        List<String> list = new ArrayList<>();
        dfs(s, list, 0);
        return res;
    }

    private void dfs(String s, List<String> list, int start) {
        if (start >= s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String sb = s.substring(start, i + 1);
            if (!isValid(sb)) continue;
            list.add(sb);
            dfs(s, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 判断一个字符串是否是回文串
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }


    /**
     * 二进制表示质数个计算值位（简单题）
     *
     * @param left
     * @param right
     * @return
     */
    public static int countPrimeSetBits(int left, int right) {
        int sum = 0;
        for (int i = left; i <= right; i++) {
            int x = toTwoSum(i);
            if (x == 0 || x == 1) continue;
            if (isValid(x)) sum++;
        }
        return sum;
    }

    private static boolean isValid(int num) {
        if (num == 2) return true;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static int toTwoSum(int num) {
        int ret = 0;
        while (num != 0) {
            num = num & (num - 1);
            ret++;
        }
        return ret;
    }


    /**
     * 组合总数4
     *
     * @param nums
     * @param target
     * @return
     */
    Map<Integer, Integer> map = new HashMap<>();

    public int combinationSum4_2(int[] nums, int target) {
        return dfs(nums, target);
    }

    private int dfs(int[] nums, int target) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (map.containsKey(target)) return map.get(target);
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res += dfs(nums, target - nums[i]);
        }
        map.put(target, res);
        return res;
    }


    /**
     * 单词拆分
     *
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] flag = new boolean[len + 1];
        flag[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (flag[j] && set.contains(s.substring(j, i))) {
                    flag[i] = true;
                    break;
                }
            }
        }
        return flag[len];
    }


    /**
     * 接雨水
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        // 寻找该点左边的最大高度与右边的最大高度取最小值-height[i]
        int len = height.length;
        int[] larr = new int[len];  //  记录下标的左边最大高度
        int[] rarr = new int[len];  //  记录下标右边的最大高度
        int lmax = 0;
        int rmax = 0;
        // 保存左边最大值
        for (int i = 0; i < len; i++) {
            if (i == 0) larr[0] = 0;
            else {
                lmax = Math.max(lmax, height[i - 1]);
                larr[i] = lmax;
            }
        }
        // 保存右边最大值
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) rarr[i] = 0;
            else {
                rmax = Math.max(rmax, height[i + 1]);
                rarr[i] = rmax;
            }
        }
        // 当前点能接到雨水的最大值为 Min(larr,rarr)-height[i];
        int sum = 0;
        for (int i = 1; i < len - 1; i++) {
            int x = Math.min(larr[i], rarr[i]) - height[i];
            if (x > 0) sum += x;
        }
        return sum;
    }
}
