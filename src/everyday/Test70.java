package everyday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test70 {

    public static void main(String[] args) {
        new Test70().permuteUnique(new int[]{1, 1, 2});
    }

    public static int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int min = 11, max = -1;
        int preMin = 0;
        int kind = 0;
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
            if (preMin == min && max == i) {
                kind++;
                preMin = max + 1;
                min = preMin;
            }
        }
        return kind;
    }


    static int total = 0;

    // https://leetcode.cn/problems/distinct-subsequences-ii/
    public static int distinctSubseqII(String s) {
        if (s == null || s.length() == 0) return 0;

        return total;
    }

    boolean[] vis;

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> perm = new ArrayList<Integer>();
        vis = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, ans, 0, perm);
        return ans;
    }

    public void backtrack(int[] nums, List<List<Integer>> ans, int idx, List<Integer> perm) {
        if (idx == nums.length) {
            ans.add(new ArrayList<Integer>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i] || i > 0 && nums[i] == nums[i - 1] && vis[i - 1]) continue;
            perm.add(nums[i]);
            vis[i] = true;
            backtrack(nums, ans, idx + 1, perm);
            perm.remove(perm.size() - 1);
            vis[i] = false;
        }
    }

}
