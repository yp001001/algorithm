package test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test15 {

    public static void main(String[] args) {
        char[][] board = new char[][]{{'a'}};
        new Test15().exist(board, "a");
    }


    /**
     * 组合总数II
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> res;
    List<Integer> list;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        Arrays.sort(candidates);
        dfs(candidates, target, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, int start) {
        if (target < 0) {
            return;
        }
        if (0 == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            list.add(candidates[i]);
            dfs(candidates, target - candidates[i], i + 1);
            list.remove(list.size() - 1);
        }
    }


    boolean[][] marked;

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        marked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, String word, int i, int j, int path) {
        if (path == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || word.charAt(path) != board[i][j] || marked[i][j]) {
            return false;
        }
        marked[i][j] = true;
        boolean res = dfs(board, word, i, j - 1, path + 1) || dfs(board, word, i, j + 1, path + 1) || dfs(board, word, i - 1, j, path + 1) || dfs(board, word, i + 1, j, path + 1);
        marked[i][j] = false;
        return res;
    }

}
