package com.yp.tree.uf_tree;

public class UF_Tree_Weighted {
    // 存储的是父节点的索引
    private int[] eleAndGroup;
    private int count;
    // 永安里存储每个更节电对应的树中保存的节点的个数
    private int[] sz;

    public UF_Tree_Weighted(int N) {
        this.count = N;
        this.eleAndGroup = new int[N];
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
        sz = new int[N];
        // 默认情况下，sz每个索引处的值都是1
        for (int i = 0; i < sz.length; i++) {
            sz[i] = 1;
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

        // 判断pRoot的树大，还是qRoot树大，最终将小树移动到大树
        if (sz[pRoot] > sz[qRoot]) {
            eleAndGroup[qRoot] = eleAndGroup[pRoot];
            // 修改sz
            sz[pRoot] += sz[qRoot];
        } else {
            eleAndGroup[pRoot] = eleAndGroup[qRoot];
            sz[qRoot] += sz[pRoot];
        }

        count--;
    }

}
