package com.yp.graph;

import com.yp.tree.uf_tree.UF_Tree_Weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BreadthFirstPathsTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(BreadthFirstPathsTest.class.getClassLoader().getResourceAsStream("file/road_find.txt")));
        int l = Integer.parseInt(reader.readLine());
        UF_Tree_Weighted tree = new UF_Tree_Weighted(l);
        int l2 = Integer.parseInt(reader.readLine());
        Graph g = new Graph(l);
        for (int i = 0; i < l2; i++) {
            String[] split = reader.readLine().split(" ");
            g.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        BreadthFirstPaths paths = new BreadthFirstPaths(g, 0);
        System.out.println(paths.hasPathTo(4));
        Stack<Integer> stack = paths.pathTo(4);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + "\t");
        }
    }
}
