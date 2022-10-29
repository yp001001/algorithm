package com.yp2.graph;

import java.util.Stack;

public class DepthFirstPaths {
    private boolean[] marked;   // 索引代表顶点，值表示当前顶点是否已经被搜索过
    private int s;              // 起点
    private int[] edgeTo;       // 索引代表顶点，值表示从起点s到当前顶点路径上的最后一个顶点


    public DepthFirstPaths(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.s = s;
        this.edgeTo = new int[G.V()];
        dfs(G, s);
    }

    // 使用深度优先搜索找出G图中v顶点的所有相邻顶点
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    // 判断v顶点与s顶点是否存在路径
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // 找出从起点s到顶点v的路径
    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> stack = new Stack<>();
        for (int i = v; i != s; i = edgeTo[v]) {
            stack.push(i);
        }
        stack.push(s);
        return stack;
    }
}
