package com.yp.graph;

import com.yp.tree.queue.IndexMinPriorityQueue;

/*
Prim算法API设计
 */
public class PrimMST {
    // 索引代表顶点，值表示当前顶点和最小生成树之间的最短边
    private Edge[] edgeTo;
    // 索引代表顶点，值表示当前顶点和最小生成树之间的最短边的权重
    private double[] distTo;
    // 索引代表顶点，如果当前顶点已经在树中，则值为true，否则为false
    private boolean[] marked;
    // 存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    // 根据一副加权无向图，创建最小生成树计算对象
    PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        // 初始化marked
        marked = new boolean[G.V()];
        // 初始化pq
        pq = new IndexMinPriorityQueue<Double>(G.V());

        // 默认让顶点0进入树中，但是树中只有一个顶点0，因此0顶点默认没有和其它的顶点相连
        // 所以让distTo对应位置处的值存0.0
        distTo[0] = 0.0;
        pq.insert(0, 0.0);

        // 遍历索引最小有限队列，拿到最小和N切边对应的顶点，把该顶点加入到最小生成树中
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    // 将顶点v添加到最小生成树中，并且更新数据
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        // 得到与顶点关联的所有边
        for (Edge e : G.adj(v)) {
            // 判断当前顶点是否已被查询过
            int w = e.other(v);
            if (marked[w]) {
                continue;
            }
            // 查询边的权重，如果小，就更新
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeItem(w, e.weight());
                } else {
                    pq.insert(w, e.weight());
                }
            }
        }
    }

    // 获取最小生成树的所有边
    public Queue<Edge> edges() {
        Queue<Edge> edges = new Queue<>();
        for (int i = 0; i < edgeTo.length; i++) {
            if (edgeTo[i] != null) {
                edges.enqueue(edgeTo[i]);
            }
        }
        return edges;
    }

}
