package test2;

import java.util.*;

public class Test28 {

    public static void main(String[] args) {
        Test28 test28 = new Test28();
//        String[] permutation = test28.permutation("aaab");
//        System.out.println(Arrays.toString(permutation));

        int index = test28.search(new int[]{5, 1, 3}, 5);
        System.out.println(index);
    }


    /**
     * 左边的较小数与右边的较大数交换后，以能够让当前排列变大
     * 这个较小数尽量靠右，较大数尽可能小
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length - 1;
        int index = n - -1;
        // 判断是否是最大序列
        while (index >= 0 && nums[index] >= nums[index + 1]) {
            index--;
        }
        if (index >= 0) {
            int j = nums.length - 1;
            // 找到临近的大于nums[index]的最小值
            while (j >= 0 && nums[j] <= nums[index]) {
                j--;
            }
            swap(nums, index, j);
        }
        reverse(nums, index + 1);
    }


    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    void reverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; i < j; i++, j--) {
            swap(nums, i, j);
        }
    }


    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                dp[i + 1] = 0;  // 为 ‘(’ 则一定不是有效括号
            } else {
                if (!stack.isEmpty()) {
                    int pop = stack.pop();
                    int len = i - pop + 1;
                    dp[i + 1] = len + dp[i - len + 1];
                }
            }
        }
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }


    //=================================================================================

    // 打印字符串的全部排列，不能重复
    boolean[] marked;
    List<String> res;

    public String[] permutation(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        marked = new boolean[n];
        res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(chars, sb);
        return res.toArray(new String[0]);
    }

    // 将s改成char[]后进行排序
    private void dfs(char[] chars, StringBuilder sb) {
        if (sb.length() == chars.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            // 前一个被用过
            if (marked[i] || (i > 0 && !marked[i - 1] && chars[i] == chars[i - 1])) continue;
            sb.append(chars[i]);
            marked[i] = true;
            dfs(chars, sb);
            marked[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            if (target == nums[0]) return 0;
            else return -1;
        }
        //找到旋转点
        int l = 0, r = nums.length - 1, ans = 0;
        while (l < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        ans = l;
        if (target <= nums[nums.length - 1]) {
            return binarySearch(nums, target, ans, nums.length - 1);
        }
        return binarySearch(nums, target, 0, ans - 1);
    }

    int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
