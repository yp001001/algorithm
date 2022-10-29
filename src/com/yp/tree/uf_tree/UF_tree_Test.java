package com.yp.tree.uf_tree;

import java.util.Scanner;

public class UF_tree_Test {

    public static void main(String[] args) {
        UF uf = new UF(5);
        System.out.println("默认情况下的分组：" + uf.count());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入第一个合并元素");
            int p = scanner.nextInt();
            System.out.println("请输入第二个合并元素");
            int q = scanner.nextInt();

            if (uf.connected(p, q)) {
                System.out.println("两个元素已经在同一组了");
                continue;
            }
            uf.union(p, q);
            System.out.println("当前并查集中还有：" + uf.count() + "个分组");
        }
    }
}
