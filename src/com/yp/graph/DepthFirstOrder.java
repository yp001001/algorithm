package com.yp.graph;

import java.util.Stack;

/*
顶点排序API设计
 */
public class DepthFirstOrder {
    // 索引代表顶点，值表示当前顶点是否已被搜索
    private boolean[] marked;
    // 使用栈，存储顶点序列
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        marked = new boolean[G.V()];
        reversePost = new Stack<>();
        for (int i = 0; i < marked.length; i++) {
            marked[i] = false;
        }
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                dfs(G, i);
            }
        }
    }

    // 基于深度优先搜索，生成顶点线性序列
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (Integer w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        reversePost.push(v);
    }

    // 获取顶点线性序列
    public Stack<Integer> reversePost() {
        return reversePost;
    }

}
