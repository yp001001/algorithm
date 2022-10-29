package com.yp.graph;

import java.util.Stack;

public class BreadthFirstPaths {
    // 索引代表顶点，值表示当前顶点是否已被搜索
    private boolean[] marked;
    // 起点
    private int s;
    // 索引代表顶点，值表示从起点s到当前顶点路径的最后一个顶点
    private int[] edgeTo;

    //构造广度优先搜索对象，使用广度优先搜索找出G图中起点为s的所有路径
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        this.s = s;
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    // 使用广度优先搜索找出G图中v顶点的所有相邻顶点
    private void bfs(Graph G, int v) {
        marked[s] = true;
        // 存储临时元素
        Queue<Integer> queue = new Queue<Integer>();
        for (Integer w : G.adj(v)) {
            marked[w] = true;
            edgeTo[w] = s;
            queue.enqueue(w);
        }
        while (!queue.isEmpty()) {
            Integer x = queue.dequeue();
            for (Integer w : G.adj(x)) {
                if (!marked[w]) {
                    queue.enqueue(w);
                    marked[w] = true;
                    edgeTo[w] = x;
                }
            }

        }
    }


    //判断v顶点与s顶点是否存在相通路径
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    // 找出从顶点s到顶点v的路径（就是该路径经过的顶点）
    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        // 创建栈对象，保存路径中所有的顶点
        Stack<Integer> stack = new Stack<>();
        while (edgeTo[v] != s) {
            stack.push(v);
            v = edgeTo[v];
        }
        stack.push(v);
        stack.push(s);
        return stack;
    }
}
