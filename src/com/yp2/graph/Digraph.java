package com.yp2.graph;

import com.yp.graph.Queue;

public class Digraph {

    private final int v;
    private int E;                  // 记录图中边的数量
    private Queue<Integer>[] adj;

    public Digraph(int v) {
        this.v = v;
        this.E = 0;
        this.adj = new Queue[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Queue<>();
        }
    }

    public int V() {
        return this.v;
    }

    public int E() {
        return this.E;
    }

    public void addEdge(int v, int w) {
        adj[v].enqueue(w);
    }

    public Queue<Integer> adj(int v) {
        return adj[v];
    }

    // 得到该图的反向图
    private Digraph reverse() {
        Digraph digraph = new Digraph(v);
        for (int i = 0; i < v; i++) {
            for (Integer x : adj[i]) {
                digraph.addEdge(x, i);
            }
        }
        return digraph;
    }
}
