package com.yp.graph;

import com.yp.tree.uf_tree.UF_Tree_Weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class DepthFirstPathsTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(DepthFirstPathsTest.class.getClassLoader().getResourceAsStream("file/road_find.txt")));
        int l = Integer.parseInt(reader.readLine());
        UF_Tree_Weighted tree = new UF_Tree_Weighted(l);
        int l2 = Integer.parseInt(reader.readLine());
        Graph g = new Graph(l);
        for (int i = 0; i < l2; i++) {
            String[] split = reader.readLine().split(" ");
            g.addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }

        DepthFirstPaths paths = new DepthFirstPaths(g, 0);
        System.out.println(paths.hasPathTo(2));
        Stack<Integer> stack = paths.pathTo(2);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + "\t");
        }
    }
}
