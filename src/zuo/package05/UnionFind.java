package zuo.package05;

public class UnionFind {
    // 保存父节点
    private int[] parent;
    // 集合大小
    private int[] size;
    // 帮助节点
    private int[] help;
    // 集合个数
    private int sets;

    public UnionFind(int N) {
        parent = new int[N];
        size = new int[N];
        help = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        sets = N;
    }

    public int find(int index) {
        int p = 0;
        while (index != parent[index]) {
            help[p++] = index;
            index = parent[index];
        }
        for (int i = 0; i < p; i++) {
            parent[help[i]] = index;
        }
        return index;
    }

    public void union(int a, int b) {
        int f1 = find(a);
        int f2 = find(b);
        if (f1 != f2) {
            sets--;
            if (size[f1] > size[f2]) {
                size[f1] += size[f2];
                parent[f2] = parent[f1];
            } else {
                size[f2] += size[f1];
                parent[f1] = parent[f2];
            }

        }
    }

    public int sets() {
        return sets;
    }
}
