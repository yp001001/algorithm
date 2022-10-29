package test;

import java.util.*;

public class Test27 {

    public static void main(String[] args) {
        Test27 test27 = new Test27();
        test27.combine(3, 2);
    }

    /**
     * 两个元素的交集
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> set = new HashSet<>();
        int idx_1 = 0, idx_2 = 0;
        while (idx_1 < nums1.length && idx_2 < nums2.length) {
            if (nums1[idx_1] == nums2[idx_2]) {
                set.add(nums1[idx_1]);
                idx_1++;
                idx_2++;
            } else if (nums1[idx_1] < nums2[idx_2]) {
                idx_1++;
            } else {
                idx_2++;
            }
        }
        int[] nums = new int[set.size()];
        int idx = 0;
        for (int x : set) {
            nums[idx++] = x;
        }
        return nums;
    }


    /**
     * 快排
     */
    private void quickSort(int[] nums, int left, int right, int k) {
        if (left < right) {
            int l = left;
            int r = right;
            int temp = nums[l];
            while (l < r) {
                while (l < r && nums[r] >= temp) {
                    r--;
                }
                while (l < r && nums[l] <= temp) {
                    l++;
                }
                if (l < r) {
                    swap(nums, l, r);
                }
            }
            swap(nums, left, r);
            if (nums.length - r <= k) quickSort(nums, left, r - 1, k);
            else quickSort(nums, r + 1, right, k);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int a = nums[i];
        nums[i] = nums[j];
        nums[j] = a;
    }


    /**
     * 数组相对排序
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int max = 0;
        // 1. 找到arr1数组中的最大值
        for (int i : arr1) {
            max = Math.max(max, i);
        }
        // 2. 将数据作为下标保存在nums中
        int[] nums = new int[max + 1];
        for (int x : arr1) {
            nums[x]++;
        }
        int[] res = new int[arr1.length];
        int idx = 0;
        for (int i = 0; i < arr2.length; i++) {
            int c = nums[arr2[i]];
            while (c > 0) {
                res[idx++] = arr2[i];
                c--;
            }
            nums[arr2[i]] = 0;
        }
        for (int i = 1; i <= max; i++) {
            int c = nums[i];
            while (c > 0) {
                res[idx++] = i;
                c--;
            }
        }
        return res;
    }


    /**
     * 含有 K 个元素的组合
     *
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> res;

    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(n, k, list, 1);
        return res;
    }

    private void dfs(int n, int k, List<Integer> list, int start) {
        if (list.size() == k) {
            res.add(new ArrayList(list));
            return;
        }
        if (start > n) return;
        for (int i = start; i <= n; i++) {
            list.add(i);
            dfs(n, k, list, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
