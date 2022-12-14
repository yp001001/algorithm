package com.yp.graph;

/*
检测有向环的API
 */
public class DirectedCycle {
    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //记录图中是否有环
    private boolean hasCycle;
    //索引代表顶点，使用栈的思想，记录当前顶点有没有已经处于正在搜索的有向路径上
    private boolean[] onStack;

    //创建一个检测环对象，检测图G中是否有环
    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        hasCycle = false;
        onStack = new boolean[G.V()];
        for (int i = 0; i < G.V(); i++) {
            marked[i] = false;
            onStack[i] = false;
        }
        for (int i = 0; i < G.V(); i++) {
            if(!marked[i]) {
                dfs(G, i);
            }
        }
    }

    //基于深度优先搜索，检测图G中是否有环
    private void dfs(Digraph G, int v) {
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

    //判断当前有向图G中是否有环
    public boolean hasCycle() {
        return hasCycle;
    }

}
