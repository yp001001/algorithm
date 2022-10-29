package com.yp.tree;

import sun.misc.Queue;

public class BinaryTree2Test {
    public static void main(String[] args) throws InterruptedException {
        BinaryTree2<Integer, String> tree = new BinaryTree2<>();

        tree.put(1, "张三");
        tree.put(2, "李四");
        tree.put(3, "王五");
        tree.put(4, "王五");
        tree.put(-1, "赵六");

        System.out.println("树的最大深度："+tree.maxDepth());

//        System.out.println(tree.size());
//        System.out.println(tree.get(2));

//        tree.delete(2);
//        System.out.println(tree.size());
//        System.out.println(tree.get(3));
//
//        tree.pre();

//        Queue<Integer> queue = tree.layerErgodic();
//        while (!queue.isEmpty()) {
//            Integer key = queue.dequeue();
//            String value = tree.get(key);
//            System.out.println(key + "-" + value);
//        }
    }
}
