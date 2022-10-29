package com.yp2.graph;

public class DepthFirstSearch {

    private boolean[] marked; // 索引代表顶点，值表示当前顶点是否已经被搜索过
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.count = 0;
        dfs(G, s);
    }


    //  找到所有与v相通的顶点
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (Integer itr : G.adj(v)) {
            if (!marked[itr]) {
                dfs(G, itr);
            }
        }
        count++;
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
