package test;

import java.util.ArrayList;
import java.util.List;

public class Test29 {

    public static void main(String[] args) {
        Test29 test29 = new Test29();
        int[] ints = test29.shortestToChar("loveleetcode", 'e');
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
    }

    public int[] shortestToChar(String s, char c) {
        int size = s.length();
        int[] res = new int[size];
        int idx = -size;    // 前面没有c的时候我们让其为最大值
        // 从左边扫描，得到下标
        for (int i = 0; i < size; i++) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = i - idx;
        }
        // 从右边扫描
        idx = Integer.MAX_VALUE;
        for (int i = size - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            res[i] = Math.min(idx - i, res[i]);
        }

        return res;
    }


    /**
     * 含有重复数字的整数数组
     *
     * @param candidates
     * @param target
     * @return
     */
    boolean[] marked;
    List<List<Integer>> res;
    List<Integer> list;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int size = candidates.length;
        marked = new boolean[size];
        list = new ArrayList<>();
        res = new ArrayList<>();
        dfs(candidates, target, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }
        if (target < 0 || start == candidates.length) return;
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            marked[i] = true;
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1);
            marked[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
