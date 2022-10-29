package com.yp.graph;

public class BreadthFirstSearch {
    // 索引代表顶点，值表示当前顶点是否已被搜索
    private boolean[] marked;
    // 记录有多少个顶点与s顶点相通
    private int count;
    // 用来存储待搜索临接表的点
    private Queue<Integer> waitSearch;

    // 构造广度优先搜索对象，使用广度优先搜索找到G图中顶点的所有相邻顶点
    public BreadthFirstSearch(Graph G, int s) {
        // 顶点个数就是marked长度
        marked = new boolean[G.V()];
        this.count = 0;
        waitSearch = new Queue<>();
        bfs(G, s);
    }

    // 使用广度优先搜索找出G图中v顶点的所有相邻顶点
    private void bfs(Graph G, int v) {
        for (Integer w : G.adj(v)) {
            waitSearch.enqueue(w);
            marked[w] = true;
        }
        while (!waitSearch.isEmpty()) {
            Integer w = waitSearch.dequeue();
            addQ(G.adj(w));
            count++;
        }

    }

    private void addQ(Queue<Integer> queue) {
        for (Integer w : queue) {
            if (!marked[w]) {
                waitSearch.enqueue(w);
                marked[w] = true;
            }
        }
    }

    // 判断w顶点是否与s顶点相通
    public boolean marked(int w) {
        return marked[w];
    }

    // 获取与顶点相通的所有顶点的总数
    public int count() {
        return count;
    }
}
