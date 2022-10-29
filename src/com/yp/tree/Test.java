package com.yp.tree;


public class Test {
    public static void main(String[] args) {
        TwoSortTree<String> sortTree = new TwoSortTree<>();
        sortTree.add("E");
        sortTree.add("B");
        sortTree.add("C");
        sortTree.add("A");
        sortTree.add("V");
        sortTree.add("D");
        sortTree.pre();
    }
}
