package everyday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test69 {

    public static void main(String[] args) {
        int[] nums = {82, 288, 1, 32, 0, 11, 20, 2, 1, -10};
        mergeSort(nums);
        System.out.println(Arrays.toString(nums));
    }


    /* 归并排序 */
    public static void mergeSort(int[] arr) {
        merge(arr, 0, arr.length - 1);
    }

    private static void merge(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) >>> 1;
            merge(arr, left, mid);
            merge(arr, mid + 1, right);
            sort(arr, left, mid, right);
        }
    }

    private static void sort(int[] arr, int left, int mid, int right) {
        int l = left, r = mid + 1;
        int[] temp = new int[right - left + 1];
        int index = 0;
        while (l <= mid && r <= right) {
            if (arr[l] < arr[r]) {
                temp[index++] = arr[l++];
            } else {
                temp[index++] = arr[r++];
            }
        }
        while (l <= mid) temp[index++] = arr[l++];
        while (r <= right) temp[index++] = arr[r++];
        index = 0;
        for (int i = left; i <= right; i++) {
            arr[i] = temp[index++];
        }
    }


    // https://leetcode.cn/problems/score-of-parentheses/
    // 如果前面存在 '(' 包括了该数，则 scope * 2
    public static int scoreOfParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int ans = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') count++;
            else {
                count--;
                if (s.charAt(i - 1) == '(') ans += 1 << count;
            }
        }
        return ans;
    }


    // https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/solution/shi-xu-lie-di-zeng-de-zui-xiao-jiao-huan-ux2y/
    public int minSwap(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 当前交换与否
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1] &&
                    nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]) {
                dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1]);
                dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]) + 1;
            } else if (nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]) {
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;
            } else {
                dp[i][0] = dp[i - 1][1];
                dp[i][1] = dp[i - 1][0] + 1;
            }
        }
        return Math.min(dp[n - 1][0], dp[n - 1][1]);
    }


    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        List<Integer> diff = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff.size() >= 2) {
                    return false;
                }
                diff.add(i);
            }
        }
        if (diff.isEmpty()) {
            return true;
        }
        if (diff.size() != 2) {
            return false;
        }
        return s1.charAt(diff.get(0)) == s2.charAt(diff.get(1)) && s1.charAt(diff.get(1)) == s2.charAt(diff.get(0));
    }
}
