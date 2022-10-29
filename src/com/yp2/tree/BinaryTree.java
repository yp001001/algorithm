package com.yp2.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 */
public class BinaryTree<Key extends Comparable<Key>, Value> {

    private Node root;
    private int N;

    public BinaryTree() {
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    /**
     * 给指定树x上，添加键一个键值对，并返回添加后的新树
     *
     * @param x
     * @param key
     * @param val
     * @return
     */
    private Node put(Node x, Key key, Value val) {

        if (x == null) {
            N++;
            return new Node(key, val, null, null);
        }

        int cmp = x.key.compareTo(key); //
        if (cmp < 0) {
            x.right = put(x.right, key, val);
        } else if (cmp > 0) {
            x.left = put(x.left, key, val);
        } else {
            x.value = val;
        }
        return x;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (root == null) {
            return null;
        }
        int cmp = x.key.compareTo(key);
        if (cmp < 0 && x.right != null) {
            return get(x.right, key);
        } else if (cmp > 0 && x.left != null) {
            return get(x.left, key);
        } else if (cmp == 0) {
            return x.value;
        }
        return null;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(key);
        if (cmp < 0) {
            x.right = delete(x.right, key);
        } else if (cmp > 0) {
            x.left = delete(x.left, key);
        } else {
            N--;
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            // 将右子树的最左节点作为x节点
            Node n = x.right;
            while (n.left != null) {
                if (n.left.left == null) {
                    n.left.right = x.left; // 将最左节点的右子节点作为父节点的左子节点
                    n.left = null;
                } else {
                    n = n.left;
                }
            }
            // 将n节点作为x节点
            n.left = x.left;
            n.right = x.right;
            x = n;
        }
        return x;
    }

    /**
     * 寻找二叉查找树的最小键
     *
     * @return
     */
    public Node min() {
        if (root == null) {
            return null;
        }
        return min(root);
    }

    private Node min(Node x) {
        if (x.left != null) {
            return min(x.left);
        } else {
            return x;
        }
    }


    public Node max() {
        return max(root);
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        Node n = x;
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    public int size() {
        return N;
    }

    public void pre() {
        pre(root);
    }

    private void pre(Node x) {
        if (x == null) {
            return;
        }
        System.out.println("key：" + x.key + "- value：" + x.value);
        pre(x.left);
        pre(x.right);
    }


    public void mid() {
        mid(root);
    }

    private void mid(Node x) {
        if (x == null) {
            return;
        }
        // 先到左子树
        mid(x.left);
        System.out.println("key：" + x.key + "- value：" + x.value);
        // 最后右子树
        mid(x.right);
    }

    public void last() {
        last(root);
    }

    private void last(Node x) {
        if (x == null) {
            return;
        }
        last(x.left);
        last(x.right);
        System.out.println("key：" + x.key + "- value：" + x.value);
    }

    /**
     * 层序遍历
     *
     * @return
     */
    public Queue<Value> layer() {
        return layer(root);
    }


    /**
     * 查询树的最大深度
     *
     * @return
     */
    public int maxLen() {
        return maxLen(root);
    }

    private int maxLen(Node x) {
        if (x == null) {
            return 0;
        }
        int left, right;
        left = maxLen(x.left);
        right = maxLen(x.right);
        return left > right ? left + 1 : right + 1;
    }


    private Queue<Value> layer(Node x) {
        if (x == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        Queue<Value> result = new LinkedList<>();
        queue.offer(x);
        while (!queue.isEmpty()) {
            // 将队列头元素弹出
            Node node = queue.poll();
            // 保存value
            result.add(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return result;
    }


    public class Node {
        // 存储键
        public Key key;
        public Value value;

        // 记录左子节点
        public Node left;
        private Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

}
