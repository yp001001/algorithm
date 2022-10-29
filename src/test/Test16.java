package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test16 {

    public static void main(String[] args) {
    }


    /**
     * 有效的数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] row = new int[9][9];
        int[][] column = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    int index = c - '0' - 1;
                    row[i][index]++;
                    column[index][j]++;
                    subboxes[i / 3][j / 3][index]++;
                    if (row[i][index] > 1 || column[index][j] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int x = 0;
        int len = digits.length;
        int n = 0;
        for (int i = len - 1; i >= 0; i--) {
            if (i == len - 1) n = digits[i] + 1;
            else n = digits[i] + x;
            list.add(n % 10);
            x = n / 10;
        }
        if (x == 1) {
            list.add(1);
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(list.size() - i - 1);
        }
        return nums;
    }

    private static void swap(int[] nums, int a, int b) {
        int t = nums[a];
        nums[a] = nums[b];
        nums[b] = t;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);      // 进行排序
        int len = nums.length;
        int x = len - 1;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            for (int j = i + 1; j < x; j++) {
                if (nums[j] == nums[j - 1]) continue;
                // 从右往左遍历
                while (x > j) {
                    if (nums[j] + nums[x] + target == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[x]);
                    } else {
                        x--;
                    }
                }
                if (x <= j) {
                    return res;
                }
            }
        }
        return res;
    }


    /**
     * 数组相对排序
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 计数排序
        int max = arr1[0];
        for (int x : arr1) {
            max = max > x ? max : x;
        }
        // arr1数组数据必须大于0
        int[] m = new int[max + 1];
        int len = arr1.length;
        for (int i = 0; i < len; i++) {
            m[arr1[i]]++;
        }
        int[] n = new int[len];
        int idx = 0;
        for (int x : arr2) {
            for (int j = 0; j < m[x]; j++) {
                n[idx++] = x;
            }
            // 置为0
            m[x] = 0;
        }
        // 应该是0-max，不能按照arr1来，否则最后是无序的
        for (int x = 0; x <= max; x++) {
            for (int i = 0; i < m[x]; i++) {
                n[idx++] = x;
            }
        }
        return n;
    }


    /***
     * 解码方法
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 1) != '0' && (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0') <= 26) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }

    public static int maxArea(int[] height) {
        int max = 0;
        int n = height.length - 1;
        int l = 0;
        int r = n;
        while (l < r) {
            int min = Math.min(height[l], height[r]);
            max = Math.max(max, (r - l) * min);
            if (min == height[l]) l++;
            else r--;
        }
        return max;
    }

}
