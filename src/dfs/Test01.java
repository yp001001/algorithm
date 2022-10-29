package dfs;

import java.util.ArrayList;
import java.util.List;

public class Test01 {

    public static void main(String[] args) {
        Test01 test01 = new Test01();
        List<List<Integer>> permute = test01.permute(new int[]{1, 2, 3});
        for (List<Integer> list : permute) {
            System.out.println(list);
        }
    }


    //===================================全排列================================

    boolean[] marked;
    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        marked = new boolean[n];
        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        dfs(nums, list);
        return res;
    }

    private void dfs(int[] nums, List<Integer> list) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (marked[i]) continue;
            list.add(nums[i]);
            marked[i] = true;
            dfs(nums, list);
            marked[i] = false;
            list.remove(list.size() - 1);
        }
    }

}
