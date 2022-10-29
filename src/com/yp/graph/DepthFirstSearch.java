package com.yp.graph;


public class DepthFirstSearch {

    // 索引代表顶点，值标识当前顶点是否已经被搜索
    private boolean[] marked;
    // 记录有多少个顶点与s顶点相通
    private int count;

    // 构造深度优先搜索对象，使用深度优先搜索找出G图中s顶点的所有相通顶点
    public DepthFirstSearch(Graph G, int s) {
        // marked长度应为顶点数量
        marked = new boolean[G.V()];
        count = 0;
        dfs(G, s);
    }

    // 使用深度优先搜索找出G图中v顶点所有的相通顶点
    private void dfs(Graph G, int v) {
        // 首先将该顶点置为true，表示已搜索
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        count++;
    }

    // 判断w顶点与s顶点是否相通
    public boolean marked(int w) {
        return marked[w];
    }

    // 获取与顶点s相通的所有顶点的总数
    public int count() {
        return count;
    }
}
