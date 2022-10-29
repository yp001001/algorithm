package jx.yp.sort.leetcode;


import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        int num = 10;
        num = Math.abs(1);
    }


    public int removeElement(int[] nums, int val) {
        if (nums == nums || nums.length == 0) {
            return 0;
        }
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            if (val != nums[i]) {
                nums[r] = nums[i];
                r++;
            }
        }
        return r;
    }

    public int count(int n) {
        int count = 0;
        while (n > 0) {
            n = n / 2;
            count++;
        }
        return count;
    }

    public int mostFrequent(int[] nums, int key) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (key == nums[i]) {
                if (i < len - 1) {
                    map.put(nums[i + 1], map.getOrDefault(nums[i + 1], 0) + 1);
                }
            }
        }
        int max = 0;
        int index = -1;
        for (int i = 0; i < len; i++) {
            if (max < (map.get(nums[i]) == null ? 0 : map.get(nums[i]))) {
                index = i;
                max = map.get(nums[i]);
            }
        }
        return nums[index];
    }


    public void dfs(int[] n, int k, List<java.util.List<Integer>> all, List<Integer> list, int idx) {
        if (idx == n.length) {
            return;
        }
        if (k == 0) {
            all.add(new ArrayList<>(list));
            return;
        }
        list.add(n[idx]);
        dfs(n, k - 1, all, list, idx + 1);
        list.remove(list.size() - 1);
        dfs(n, k - 1, all, list, idx + 1);
    }

    public boolean exist(char[][] board, String word) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                map.put(board[i][j], map.getOrDefault(board[i][j], 0) + 1);
            }
        }
        for (int i = 0; i < word.length(); i++) {
            Integer x = map.get(word.charAt(i));
            if (x == null || i <= 0) {
                return false;
            } else {
                map.put(word.charAt(i), x - 1);
            }
        }
        return true;
    }


    /***
     * 无序数组的解法
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        int index = 0; // 记录需要更换的下标
        for (int i = 0; i < len; i++) {
            int count = map.get(nums[i]) == null ? 0 : map.get(nums[i]);
            if (count == 2) {
                if (i + 1 == len) {
                    break;
                }
            } else {
                nums[index] = nums[i];
                index++;
                map.put(nums[i], count + 1);
            }
        }
        return index;
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int nl1 = 0;
        int nl2 = 0;
        int nr1 = nums1.length;
        int nr2 = nums2.length;
        int index = (nums1.length + nums2.length) / 2;
        while (index > 0) {
            if (nums1[nl1] < nums2[nl2]) {
                nl1++;
            } else {
                nl2++;
            }
            if (nums1[nr1] < nums2[nr2]) {
                nr2--;
            } else {
                nr2++;
            }
            index--;
        }
        int sum = 0;
        index = 0;
        for (int i = nl1 + 1; i < nr1; i++) {
            sum += nums1[i];
            index++;
        }
        for (int i = nl2; i < nr2; i++) {
            sum += nums1[i];
            index++;
        }
        double d = sum / index;
        return d;
    }

}
