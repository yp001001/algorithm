package everyday;


// https://leetcode.cn/problems/making-a-large-island/
public class Test56 {

    // =============================并查集=======================================

    public int largestIsland(int[][] grid) {
        //并查集
        int n = grid.length, m = grid[0].length, k = 0;
        UnionFind uf = new UnionFind(n * m, n * m, grid);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) continue;
                if (j - 1 >= 0 && grid[i][j - 1] == 1) uf.unite(i * m + j - 1, i * m + j);
                if (i - 1 >= 0 && grid[i - 1][j] == 1) uf.unite((i - 1) * m + j, i * m + j);
                k++;
            }
        }
        if (uf.getCount() < 3) return n * m;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) continue;
                int x1 = i - 1 < 0 || grid[i - 1][j] == 0 ? -1 : uf.find((i - 1) * m + j);
                int x2 = j - 1 < 0 || grid[i][j - 1] == 0 ? -1 : uf.find(i * m + j - 1);
                int x3 = i + 1 >= n || grid[i + 1][j] == 0 ? -1 : uf.find((i + 1) * m + j);
                int x4 = j + 1 >= m || grid[i][j + 1] == 0 ? -1 : uf.find(i * m + j + 1);
                int Area = x1 < 0 ? 0 : uf.area[x1];
                if (x2 >= 0 && x2 != x1) Area += uf.area[x2];
                if (x3 >= 0 && x3 != x2 && x3 != x1) Area += uf.area[x3];
                if (x4 >= 0 && x4 != x3 && x4 != x2 && x4 != x1) Area += uf.area[x4];
                max = Math.max(max, Area + 1);
                if (max == k + 1) return max;
            }
        }
        return max;
    }
}

class UnionFind {
    int[] root = null;
    int[] size = null;
    int[] area = null;
    int setCount;
    int n;

    public UnionFind(int n, int sum, int[][] grid) {
        root = new int[n];
        size = new int[n];
        area = new int[n];
        setCount = sum;
        for (int i = 0; i < n; ++i) {
            size[i] = 0;
            root[i] = i;
            area[i] = grid[i / grid[0].length][i % grid[0].length];
        }
    }

    public int find(int x) {
        if (root[x] == x) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    public boolean unite(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;
        if (size[x] > size[y]) {
            area[x] += area[y];
            root[y] = x;
        } else if (size[x] < size[y]) {
            area[y] += area[x];
            root[x] = y;
        } else {
            area[x] += area[y];
            root[y] = x;
            size[x] += 1;
        }
        setCount--;
        return true;
    }

    public int getCount() {
        return setCount;
    }

    // =============================timeout=====================================

    public int largestIsland_timeout(int[][] grid) {
        int maxGrid = 0;
        boolean flag = false;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    flag = true;
                    int[][] tmp = copyArr(grid);
                    tmp[i][j] = 1;
                    maxGrid = Math.max(maxGrid, process(tmp, i, j));
                }
            }
        }
        return flag ? maxGrid : n * m;
    }

    private int process(int[][] arr, int i, int j) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] != 1)
            return 0;
        arr[i][j] = 2;
        int a = process(arr, i - 1, j);
        int b = process(arr, i + 1, j);
        int c = process(arr, i, j + 1);
        int d = process(arr, i, j - 1);
        return a + b + c + d + 1;
    }

    public int[][] copyArr(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int[][] res = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                res[i][j] = grid[i][j];
            }
        }
        return res;
    }

}
