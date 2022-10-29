package com.yp.graph;

/*
加权无向图边的表示
 */
public class Edge implements Comparable<Edge> {
    // 顶点一
    private final int v;
    // 顶点二
    private final int w;
    // 当前边的权重
    private final double weight;

    // 通过顶点v和w，以及权重weight构造一个边对象
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // 获取边的权重值
    public double weight() {
        return weight;
    }

    // 获取边上的一个点
    public int either() {
        return v;
    }

    // 获取边上除了顶点vertex外的另一个顶点
    public int other(int vertex) {
        return vertex == v ? w : v;
    }

    // 比较当前边和参数that边的权重，如果当前边权重大，返回1，相等0，小于-1；
    @Override
    public int compareTo(Edge o) {
        return weight > o.weight ? 1 : weight == o.weight ? 0 : -1;
    }
}
