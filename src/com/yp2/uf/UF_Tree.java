package com.yp2.uf;

/**
 * 子节点保存的值都是父节点的下标，直到index=num[index],得到root
 */
public class UF_Tree {
    private int[] eleAndGroup;  // 记录节点元素和该元素所在分组的标识
    private int count;  // 记录并查集中数据的分组个数

    public UF_Tree(int N) {
        this.count = N;
        eleAndGroup = new int[N];
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }


    public int count() {
        return count;
    }

    /**
     * 判断并查集中元素p和元素q是否在同一分组
     *
     * @param p
     * @param q
     * @return
     */
    public boolean connected(int p, int q) {
        return eleAndGroup[p] == eleAndGroup[q];
    }

    // 元素p所在分组的标识符
    public int find(int p) {
        while (true) {
            if (p == eleAndGroup[p]) {
                return eleAndGroup[p];
            }
            p = eleAndGroup[p];
        }
    }

    // 把p元素所在分组和q元素所在分组合并
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        eleAndGroup[pRoot] = qRoot;
        // 应该判断是否合并成功
        count--;
    }
}
