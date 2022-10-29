package com.yp2.tree.redblack;

public class Node<Key, Value> {
    public Node left;
    public Node right;
    public Key key;
    public Value value;
    public boolean color;

    public Node(Key key, Value value, Node left, Node right, boolean color) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
        this.color = color;
    }
}
