package com.yp2.tree.test;

import com.yp2.tree.BinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeTest {
    public static void main(String[] args) {

//        Queue<Integer> queue = new LinkedList<>();
//
//        queue.offer(1);
//        queue.offer(2);
//        queue.offer(3);
//        queue.offer(4);
//        queue.offer(5);
//        System.out.println(queue.poll());
//        System.out.println(queue.poll());

        BinaryTree<Integer, String> tree = new BinaryTree<>();
        tree.put(5, "E");
        tree.put(3, "A");
        tree.put(1, "B");
        tree.put(6, "D");
        tree.put(2, "V");
        tree.put(9, "C");
        tree.put(7, "P");

        BinaryTree<Integer, String>.Node min = tree.min();
        System.out.println("key:" + min.key + "-value:" + min.value);
        System.out.println("树的最大高度：" + tree.maxLen());

        Queue<String> layer = tree.layer();
        Iterator<String> iterator = layer.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }

    }
}
