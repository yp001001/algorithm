package com.yp.graph;

import com.yp.tree.uf_tree.UF_Tree_Weighted;

import java.io.*;

public class Traffic_Project_Test {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(Traffic_Project_Test.class.getClassLoader().getResourceAsStream("file/traffic_project.txt")));
        int l = Integer.parseInt(reader.readLine());
        UF_Tree_Weighted tree = new UF_Tree_Weighted(l);
        int l2 = Integer.parseInt(reader.readLine());
        Graph g = new Graph(l);
        for (int i = 0; i < l2; i++) {
            String[] split = reader.readLine().split(" ");
            g.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        BreadthFirstSearch search = new BreadthFirstSearch(g, 9);
        boolean marked = search.marked(6);
        System.out.println("6与9是否相通：" + marked);
        System.out.println("10与9是否相通：" + search.marked(10));
    }
}
