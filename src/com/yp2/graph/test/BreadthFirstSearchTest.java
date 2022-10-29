package com.yp2.graph.test;

import com.yp2.graph.BreadthFirstSearch;
import com.yp2.graph.Graph;

public class BreadthFirstSearchTest {
    public static void main(String[] args) {
        Graph g = new Graph(13);
        g.addEdge(0, 5);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(5, 3);
        g.addEdge(5, 4);
        g.addEdge(3, 4);
        g.addEdge(4, 6);

        g.addEdge(7, 8);
        g.addEdge(9, 11);
        g.addEdge(9, 12);
        g.addEdge(9, 12);
        g.addEdge(11, 12);

        /**
         * 与起点0相同的顶点数量：7
         * 顶点5与顶点0是否相同：true
         */
        BreadthFirstSearch search = new BreadthFirstSearch(g, 0);

        int count = search.count();
        System.out.println("与起点0相同的顶点数量：" + count);

        boolean marked = search.marked(5);
        System.out.println("顶点5与顶点0是否相同：" + marked);
    }
}
