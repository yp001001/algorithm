package com.yp.graph;

/*
有向图
 */
public class Digraph {
    // 记录顶点个数
    private final int V;
    // 记录边数量
    private int E;
    // 邻接表
    private Queue<Integer>[] adj;

    // 创建一个包含V个顶点但不包含边的有向图
    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new Queue[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Integer>();
        }
    }

    // 获取图中顶点的数量
    public int V() {
        return this.V;
    }

    // 获取图中边的数量
    public int E() {
        return this.E;
    }

    // 向有向图中添加一条边v->w
    public void addEdge(int v, int w) {
        adj[v].enqueue(w);
    }

    // 获取由V指出的边所连接的所有顶点
    public Queue<Integer> adj(int v) {
        return adj[v];
    }

    // 该图的反向图
    private Digraph reverse() {
        Digraph digraph = new Digraph(V);
        // 遍历反转
        for (int v = 0; v < V; v++) {
            for (Integer w : adj[v]) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }
}
