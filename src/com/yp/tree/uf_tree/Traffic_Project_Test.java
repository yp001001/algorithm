package com.yp.tree.uf_tree;

import java.io.*;

public class Traffic_Project_Test {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(Traffic_Project_Test.class.getClassLoader().getResourceAsStream("file/traffic_project.txt")));
        int l = Integer.parseInt(reader.readLine());
        UF_Tree_Weighted tree = new UF_Tree_Weighted(l);
        int l2 = Integer.parseInt(reader.readLine());
        for (int i = 0; i < l2; i++) {
            String[] split = reader.readLine().split(" ");
            tree.union(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        System.out.println("还剩余" + (tree.count() - 1) + "道路需要修建");
    }
}
