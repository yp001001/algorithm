package zuo.package05;

import java.util.ArrayList;
import java.util.List;

public class UnionIsland {

    public static List<Integer> numIslands(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(uf.connect(position[0], position[1]));
        }
        return ans;
    }


    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;
        private int sets;
        private int row;
        private int col;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            int len = row * col;
            parent = new int[len];
            size = new int[len];
            help = new int[len];
        }

        private int index(int r, int c) {
            return r * col + c;
        }

        private int find(int i) {
            int p = 0;
            while (i != parent[i]) {
                help[p++] = i;
                i = parent[i];
            }
            for (int x = 0; x < p; x++) {
                parent[help[x]] = i;
            }
            return i;
        }


        public int connect(int i, int j) {
            int index = index(i, j);
            if (size[index] == 0) {
                parent[index] = 1;
                size[index] = 1;
                sets++;
                union(i + 1, j, i, j);
                union(i + 1, j, i, j);
                union(i + 1, j, i, j);
                union(i + 1, j, i, j);
            }
            return sets;
        }

        private void union(int i, int j, int x, int y) {
            if (i < 0 || i == row || j < 0 || j == col) return;
            int index_1 = index(i, j);
            int index_2 = index(x, y);
            if (size[index_1] == 0 || size[index_2] == 0) return;
            int f1 = find(index_1);
            int f2 = find(index_2);
            if (f1 != f2) {
                if (size[f1] >= size[f2]) {
                    size[f1] += size[f2];
                    parent[f2] = f1;
                } else {
                    size[f2] += size[f1];
                    parent[f1] = f2;
                }
                sets--;
            }

        }
    }
}
