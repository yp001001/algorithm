package com.yp.tree.search_tree;

public class RedBlockTree<Key extends Comparable<Key>, Value> {
    private Node root;
    private int N;
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public Key key;
        private Value value;
        public Node left;
        public Node right;
        public boolean color;

        public Node(Key key, Value value, Node left, Node right, boolean color) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.color = color;
        }
    }

    public RedBlockTree() {

    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    /*
    左旋
     */
    private Node rotateLeft(Node h) {
        // 获取h节点的右子节点，表示为x
        Node x = h.right;
        // 让x节点的左子节点成为h节点的右子节点
        h.right = x.left;
        // 让h成为x节点的左子节点
        x.left = h;
        // 让x节点的color属性等于h节点的color属性
        x.color = h.color;
        // 让h节点的color属性变为红色
        h.color = RED;
        return x;
    }

    /*
    右旋
     */
    private Node rotateRight(Node h) {
        // 让x节点等于h节点的左子节点
        Node x = h.left;
        // 让x节点的右子节点放入h节点的左子节点
        h.left = x.right;
        // 让x节点的右子节点为h节点
        x.right = h;
        // 让x节点的color属性等于h节点的color属性
        x.color = h.color;
        //让h节点的color属性为红色
        h.color = RED;
        return x;
    }

    /*
    颜色反转
     */
    private void flipColors(Node h) {
        // 当前节点变为红色
        h.color = RED;
        // 左右子节点变为黑色
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    /*
    在整个树上完成插入操作
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        // 判断h是否为空，如果为空则直接返回一个红色的节点
        if (h == null) {
            N++;
            return new Node(key, val, null, null, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.value = val;
        }

        // 左旋转（当当前节点的左子节点为黑色，右子节点为红色时）
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        // 右旋转（当前结点的左子节点和左子节点的左子节点都为红色）
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        // 颜色反转
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }
        return h;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return x.value;
        }
    }

    public int size() {
        return N;
    }
}
