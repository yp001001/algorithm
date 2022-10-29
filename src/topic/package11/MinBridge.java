package topic.package11;

// https://leetcode.cn/problems/shortest-bridge/
public class MinBridge {

    public int shortestBridge(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int step = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    step += process(grid, i, j);
                }
            }
        }
        return step;
    }

    private int process(int[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 0;

        return 0;
    }

}
