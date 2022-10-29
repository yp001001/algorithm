package topic.package03;

// 摘樱桃
// https://leetcode.cn/problems/cherry-pickup/
// 错误（回去的时候被覆盖...）
public class Problem04 {

    public static int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int ans = process(grid, 0, 0);
        ans += process2(grid, grid.length - 1, grid[0].length - 1);
        return ans < 0 ? 0 : ans;
    }

    private static int process2(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return -1;
        if (grid[i][j] == -1) return -1;
        if (i == 0 && j == 0)
            return 0;
        int p1 = process2(grid, i, j - 1);
        int p2 = process2(grid, i - 1, j);
        if (p1 == -1 && p2 == -1) {
            return -1;
        }
        if (grid[i][j] == 1) {
            return Math.max(p1, p2) + 1;
        }
        return Math.max(p1, p2);
    }

    private static int process(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return -1;
        if (grid[i][j] == -1) return -1;
        if (i == grid.length - 1 && j == grid[0].length - 1) {
            if (grid[grid.length - 1][grid[0].length - 1] == 1) {
                grid[grid.length - 1][grid[0].length - 1] = 0;
                return 1;
            }
            return 0;
        }
        int p1 = process(grid, i, j + 1);
        int p2 = process(grid, i + 1, j);
        if (p1 == -1 && p2 == -1) {
            return -1;
        }
        if (grid[i][j] == 1) {
            grid[i][j] = 0;
            return Math.max(p1, p2) + 1;
        }
        return Math.max(p1, p2);
    }


    public static void main(String[] args) {
        System.out.println(cherryPickup(new int[][]{{1, -1}, {1, 1}, {1, 1}}));
    }
}
