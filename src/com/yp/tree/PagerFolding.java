package com.yp.tree;

import sun.misc.Queue;

/*
折纸问题
 1. 根节点为down
 2. 每个节点的左子节点为down，右子节点为up
 */
public class PagerFolding {


    public static void main(String[] args) throws InterruptedException {
        Node<String> root = createTree(2);
        printTree(root);
    }


    public static Node<String> createTree(int N) throws InterruptedException {
        Node<String> root = null;
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                root = new Node<>("down", null, null);
            } else {
                Queue<Node> nodes = new Queue<Node>();
                nodes.enqueue(root);
                while (!nodes.isEmpty()) {
                    Node node = nodes.dequeue();
                    if (node.left != null) {
                        nodes.enqueue(node.left);
                    }
                    if (node.right != null) {
                        nodes.enqueue(node.right);
                    }
                    if (node.left == null && node.right == null) {
                        node.left = new Node("down", null, null);
                        node.right = new Node("up", null, null);
                    }
                }
            }
        }
        return root;
    }

    public static void printTree(Node<String> root) {
        // 采用中序遍历完成
        if (root == null) {
            return;
        }
        if (root.left != null) {
            printTree(root.left);
        }
        System.out.println(root.item);
        if (root.right != null) {
            printTree(root.right);
        }
    }


    // 节点类
    private static class Node<T> {
        public T item;// 存储元素
        public Node left;
        public Node right;

        public Node(T item, Node left, Node right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }
}
