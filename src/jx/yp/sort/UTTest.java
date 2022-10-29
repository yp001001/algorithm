package jx.yp.sort;

import java.util.ArrayList;
import java.util.List;

public class UTTest {
    public static void main(String[] args) {
        UTTest utTest = new UTTest();
        utTest.subsets(new int[]{1, 2, 3});
    }

    List<List<Integer>> all = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfs(nums, 0);
        return all;
    }

    public void dfs(int[] nums, int idx) {
        if (idx == nums.length) {
            return;
        }
        list.add(nums[idx]);
        all.add(new ArrayList<Integer>(list));
        dfs(nums, idx + 1);
        list.remove(list.size() - 1);
        dfs(nums, idx + 1);
    }
}
