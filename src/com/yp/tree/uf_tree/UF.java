package com.yp.tree.uf_tree;

public class UF {

    // 记录节点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    // 记录并查集中数据的分组个数
    private int count;

    public UF(int N) {
        eleAndGroup = new int[N];
        count = N;
        // 初始化分组
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }

    /*
    获取当前并查集中的数据有多少个
     */
    public int count() {
        return count;
    }

    /*
    判断并查集中元素p和元素q是否在同一分组中
     */
    public boolean connected(int p, int q) {
        return eleAndGroup[p] == eleAndGroup[q];
    }

    /*
    元素p所在分组的标识符
     */
    public int find(int p) {
        return eleAndGroup[p];
    }

    /*
    把p元素所在分组和q元素所在分组合并
     */
    public void union(int p, int q) {
        if (p >= eleAndGroup.length || p >= eleAndGroup.length) {
            return;
        }
        int pGroup = eleAndGroup[p];
        int qGroup = eleAndGroup[q];
        for (int i = 0; i < eleAndGroup.length; i++) {
            if (eleAndGroup[i] == pGroup) {
                eleAndGroup[i] = qGroup;
            }
        }
        count--;
    }
}
