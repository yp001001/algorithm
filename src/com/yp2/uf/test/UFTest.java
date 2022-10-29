package com.yp2.uf.test;

import com.yp2.uf.UF;

import java.util.Scanner;

public class UFTest {
    public static void main(String[] args) {
        UF uf = new UF(5);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入第一个要合并的元素");
            int p = scanner.nextInt();
            System.out.println("请输入第二个要合并的元素");
            int q = scanner.nextInt();
            if (uf.connected(p, q)) {
                System.out.println("已经在同一组了");
                continue;
            }
            uf.union(p, q);
            System.out.println("当前分组：" + uf.count());
        }
    }
}
