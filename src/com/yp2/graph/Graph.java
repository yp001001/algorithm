package com.yp2.graph;

import java.util.LinkedList;
import java.util.Queue;

public class Graph {

    private final int v;    // 记录顶点数量
    private int E;          // 记录边的数量
    private Queue<Integer>[] adj;   // 邻接表

    public Graph(int v) {
        this.v = v;
        this.E = 0;
        this.adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public int V() {
        return v;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].offer(w);
        adj[w].offer(v);
    }

    public Queue<Integer> adj(int v) {
        return adj[v];
    }
}
