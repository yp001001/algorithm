package com.yp2.graph;

public class DirectedCycle {

    private boolean[] marked;
    private boolean hasCycle;
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        this.marked = new boolean[G.V()];
        this.hasCycle = false;
        this.onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            dfs(G, i);
        }
    }

    /**
     * 深度优先搜索判断该图是否有环
     *
     * @param G
     * @param v
     */
    private void dfs(Digraph G, int v) {
        // 0->1，0->2，1->2
        marked[v] = true;
        onStack[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
            if (onStack[w]) {
                hasCycle = true;
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
