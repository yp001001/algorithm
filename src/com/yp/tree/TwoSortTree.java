package com.yp.tree;

/*
二叉排序树：
  所有左子节点均小于父节点，所有右子节点均大于父节点
 */
public class TwoSortTree<T extends Comparable<T>> {
    private Node root;


    private Node add(Node root, T N) {
        if (root == null) {
            root = new Node(N, null, null);
        }
        int cmp = N.compareTo(root.Key);
        if (cmp > 0) {
            root.right = add(root.right, N);
        } else if (cmp < 0) {
            root.left = add(root.left, N);
        } else {
            root.Key = N;
        }
        return root;
    }


    /*
    前序排序
     */
    private void pre(Node node) {
        System.out.println(node);
        if (node.left != null) {
            pre(node.left);
        }
        if (node.right != null) {
            pre(node.right);
        }
    }

    public void pre() {
        pre(root);
    }

    public void add(T N) {
        if (root == null) {
            root = new Node(N, null, null);
            return;
        }
        add(root, N);
    }


    private class Node {
        T Key;
        private Node left;
        private Node right;

        public Node(T key, Node left, Node right) {
            this.Key = key;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "Key=" + Key +
                    '}';
        }
    }

}
