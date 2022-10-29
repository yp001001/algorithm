package com.yp.tree.uf_tree;

public class UF_tree {
    // 存储的是父节点的索引
    private int[] eleAndGroup;
    private int count;

    public UF_tree(int N) {
        this.count = N;
        this.eleAndGroup = new int[N];
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }

    public int count() {
        return count;
    }


    public boolean conected(int p, int q) {
        return eleAndGroup[p] == eleAndGroup[q];
    }

    // 得到元素p所在的分组的标识符
    public int find(int p) {
        while (true) {
            if (p == eleAndGroup[p]) {
                return p;
            }
            p = eleAndGroup[p];
        }
    }

    // 把p元素所在分组和q元素所在分组合并
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        eleAndGroup[pRoot] = qRoot;
        count--;
    }

}
