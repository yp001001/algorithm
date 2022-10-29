package zuo.dp.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test06 {
    ExecutorService pool = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        Test06 test06 = new Test06();
        int maxArea = test06.maxAreaOfIsland(new int[][]{{1, 1}, {1, 1}});
    }


    //=====================================被围绕的岛屿================================
    int n, m;

    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) return;
        m = board[0].length;
        // 判断第一列以及最后一列是否有被连接的O
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        // 判断第一行以及最后一行是否有被连接的O
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    //=====================================岛屿的最大面积 ================================

    boolean[][] marked;
    Map<String, Integer> memo = new HashMap<>();

    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        int n = grid.length, m = grid[0].length;
        marked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxArea = Math.max(maxArea, backtrack(grid, i, j));
            }
        }
        return maxArea;
    }

    int backtrack(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || marked[i][j] || grid[i][j] == 0) {
            return 0;
        }
        marked[i][j] = true;
        String key = i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);
        int res = 1;
        res += backtrack(grid, i + 1, j);
        res += backtrack(grid, i - 1, j);
        res += backtrack(grid, i, j + 1);
        res += backtrack(grid, i, j - 1);
        memo.put(key, res);
        return res;
    }


    //=====================================飞地的数量================================

    int rows, columns;

    public int numEnclaves(int[][] grid) {
        rows = grid.length;
        columns = grid[0].length;
        for (int i = 0; i < rows; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, columns - 1);
        }
        for (int i = 0; i < columns; i++) {
            dfs(grid, 0, i);
            dfs(grid, rows - 1, i);
        }
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    // 判断是否能到到边界
    void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= rows
                || y < 0 || y >= columns
                || grid[x][y] != 1) {
            return;
        }
        grid[x][y] = 2;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
    }




}