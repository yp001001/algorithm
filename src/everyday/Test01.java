package everyday;

import java.util.ArrayList;
import java.util.List;


// https://leetcode.cn/problems/shift-2d-grid/submissions/
public class Test01 {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            ret.add(row);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 更改的下标
                int index = (i * n + j + k) % (m * n);
                // 为该点移动的位置赋值
                ret.get(index / n).set(index % n, grid[i][j]);
            }
        }
        return ret;
    }
}
