package com.yp2.graph;

import com.yp.graph.Queue;

public class BreadthFirstSearch {
    private boolean[] marked;
    private int count;
    private Queue<Integer> waitSearch;

    public BreadthFirstSearch(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.count = 0;
        this.waitSearch = new Queue<Integer>();
        bfs(G, s);
    }


    private void bfs(Graph G, int v) {
        marked[v] = true;
        waitSearch.enqueue(v);
        while (!waitSearch.isEmpty()) {
            Integer i = waitSearch.dequeue();
            for (Integer w : G.adj(i)) {
                if (!marked[w]) {
                    count++;
                    waitSearch.enqueue(w);
                }
                marked[w] = true;
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
