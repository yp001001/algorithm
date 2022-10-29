package com.yp2.uf;

public class UF {
    private int[] eleAndGroup;  // 记录节点元素和该元素所在分组的标识
    private int count;  // 记录并查集中数据的分组个数

    public UF(int N) {
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
        return eleAndGroup[p];
    }

    // 把p元素所在分组和q元素所在分组合并
    public void union(int p, int q) {
        // 1. 判断是否已经在同一分组
        if (connected(p, q)) {
            return;
        }
        for (int i = 0; i < eleAndGroup.length; i++) {
            if (eleAndGroup[i] == eleAndGroup[p]) {
                eleAndGroup[i] = eleAndGroup[q];
            }
        }
        // 应该判断是否合并成功
        count--;
    }

}
