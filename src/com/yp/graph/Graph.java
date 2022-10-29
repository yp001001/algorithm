package com.yp.graph;


public class Graph {
    // 记录顶点数量
    private final int V;
    // 记录边数量
    private int E;
    // 邻接表
    private Queue<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new Queue[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<>();
        }
    }

    // 获取图中顶点数量
    public int V() {
        return V;
    }

    // 获取图中边的数量
    public int E() {
        return E;
    }

    // 向图中添加一条边v-w
    public void addEdge(int v, int w) {
        adj[v].enqueue(w);
        adj[w].enqueue(v);
        E++;
    }

    // 获取和顶点v相邻的所有顶点
    public Queue<Integer> adj(int v) {
        return adj[v];
    }
}
