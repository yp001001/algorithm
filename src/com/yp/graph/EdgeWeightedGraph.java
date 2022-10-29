package com.yp.graph;

/*
加权无向图
 */
public class EdgeWeightedGraph {
    // 记录顶点数量
    private final int V;
    // 记录边数量
    private int E;
    // 邻接表
    private Queue<Edge>[] adj;

    // 创建一个含有V个顶点的空加权无向图
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = new Queue[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Edge>();
        }
    }

    // 获取图中顶点的数量
    public int V() {
        return V;
    }

    // 获取图中边的数量
    public int E() {
        return E;
    }

    // 向加权无向图中添加一条边
    public void addEdge(Edge e) {
        int either = e.either();
        int other = e.other(either);
        adj[either].enqueue(e);
        adj[other].enqueue(e);
        E++;
    }

    // 获取和顶点v关联的所有边
    public Queue<Edge> adj(int v) {
        return adj[v];
    }

    // 获取加权无向图的所有边
    public Queue<Edge> edges() {
        Queue<Edge> queue = new Queue<>();
        // 注意：是无向图，直接遍历会获取两遍数据，我们可以判断边的顶点小加入，大不加入
        for (int w = 0; w < V; w++) {
            for (Edge edge : adj[w]) {
                if (edge.other(w) > w) {
                    queue.enqueue(edge);
                }
            }
        }
        return queue;
    }
}
