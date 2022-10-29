package com.yp.tree.search_tree;

public class RedBlockTreeTest {

    public static void main(String[] args) {
        RedBlockTree<String, String> tree = new RedBlockTree<>();

        tree.put("1", "张三");
        tree.put("2", "李四");
        tree.put("3", "王五");
        tree.put("4", "赵六");

        String s = tree.get("2");
        System.out.println(s);
    }
}
