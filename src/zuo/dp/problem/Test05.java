package zuo.dp.problem;

import java.util.*;

public class Test05 {

    public static void main(String[] args) {
        Test05 test05 = new Test05();
//        List<List<String>> lists = test05.solveNQueens(4);
//        for (List<String> list : lists) {
//            for (int i = 0; i < list.size(); i++) {
//                System.out.println(list.get(i));
//            }
//            System.out.println("+===================");
//        }

        List<List<Integer>> lists = test05.subsetsWithDup(new int[]{1, 2, 2});
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }

    //=====================================目标和================================

    public int findTargetSumWays(int[] nums, int target) {
        return backtrack(nums, target, 0);
    }

    Map<String, Integer> memo = new HashMap<>();

    // 不能添加一个元素，判断 remain 是否等于 target ，因为 target 可能为0，我们无法确定remain初始值
    private int backtrack(int[] nums, int remain, int start) {
        if (start == nums.length) {
            if (remain == 0) {
                return 1;
            }
            return 0;
        }
        // 后面的key可能与前面的key相同，我们必须使key唯一
        String key = start + "," + remain;
        if (memo.containsKey(key)) return memo.get(key);
        int res;
        res = backtrack(nums, remain + nums[start], start + 1);
        res += backtrack(nums, remain - nums[start], start + 1);
        memo.put(key, res);
        return res;
    }


    //=====================================解数独================================

    public void solveSudoku(char[][] board) {

    }

    //=====================================旋转图像================================

    public void rotate_2(int[][] matrix) {
        int n = matrix.length;
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tmp[j][n - i - 1] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = tmp[i][j];
            }
        }
    }


    //=====================================岛屿数量================================

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '2' || grid[i][j] == '0') continue;
                dfs(grid, i, j);
                count++;
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    //=====================================N皇后问题================================

    int[] king;
    List<List<int[]>> res;

    public List<List<String>> solveNQueens(int n) {
        king = new int[n];
        res = new ArrayList<>();
        backtrack(0, n);
        List<List<String>> result = new ArrayList<>();
        for (List<int[]> list : res) {
            int[] array = list.get(0);
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < array.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(".");
                }
                sb.replace(array[i], array[i] + 1, "Q");
                stringList.add(sb.toString());
            }
            result.add(stringList);
        }
        return result;
    }

    void backtrack(int path, int n) {
        if (path == n) {
            int[] array = Arrays.copyOfRange(king, 0, king.length);
            res.add(Arrays.asList(array));
            return;
        }
        for (int i = 0; i < n; i++) {
            king[path] = i;
            if (check(path)) {
                backtrack(path + 1, n);
            }
        }
    }


    // 判断第 n 个皇后是否符合规则
    boolean check(int n) {
        for (int i = 0; i < n; i++) {
            if (king[i] == king[n] || Math.abs(n - i) == Math.abs(king[n] - king[i])) {
                return false;
            }
        }
        return true;
    }


    //=====================================子集II================================

    boolean[] marked;
    List<List<Integer>> result;
    List<Integer> list;

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        int n = nums.length;
        marked = new boolean[n];
        result = new ArrayList<>();
        list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(nums, 0);
        return result;
    }

    void backtrack(int[] nums, int start) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            // !marked[i - 1] 表示前一个没有用过
            if (marked[i] || (i > 0 && nums[i] == nums[i - 1] && !marked[i - 1])) continue;
            list.add(nums[i]);
            marked[i] = true;
            backtrack(nums, i + 1);
            list.remove(list.size() - 1);
            marked[i] = false;
        }
    }

    //=====================================被围绕的岛屿（错误）================================

    boolean[][] neg;
    Map<String, Boolean> dict = new HashMap<>();

    public void solve(char[][] board) {
        int row = board.length, column = board[0].length;
        neg = new boolean[row][column];
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < column - 1; j++) {
                String key = i + "," + j;
                if (board[i][j] == 'O' && (dict.containsKey(key) ? dict.get(key) : false || backtrack(board, i, j))) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    boolean backtrack(char[][] board, int i, int j) {
        if (board[i][j] == 'X') {
            return true;
        }

        if (board[i][j] == 'O' && (i == 0 || j == 0 ||
                i == board.length - 1 || j == board[0].length - 1)) {
            return false;
        }
        String key = i + "," + j;
        if (dict.containsKey(key)) return dict.get(key);
        neg[i][j] = true;
        boolean v1 = backtrack(board, i + 1, j);
        boolean v2 = backtrack(board, i - 1, j);
        boolean v3 = backtrack(board, i, j + 1);
        boolean v4 = backtrack(board, i, j - 1);
        neg[i][j] = false;
        boolean res = v1 && v2 && v3 && v4;
        dict.put(key, res);
        return res;
    }

}
