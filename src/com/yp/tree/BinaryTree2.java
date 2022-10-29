package com.yp.tree;


import sun.misc.Queue;

public class BinaryTree2<Key extends Comparable<Key>, Value> {
    // 记录根节点
    public Node root;
    // 记录树中元素个数
    private int N;

    private class Node {
        // 存储键
        public Key key;
        // 存储值
        private Value value;
        // 记录左子节点
        public Node left;
        // 记录右子节点
        public Node right;

        public Node(Key key, Value value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

    }

    // 获取树中元素个数
    public int size() {
        return N;
    }

    // 向树中添加元素Key-Value
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    // 向指定的树x添加Key-Value，并返回添加元素后新的树
    public Node put(Node x, Key key, Value value) {
        if (x == null) {
            x = new Node(key, value, null, null);
            N++;
            return x;
        }
        // 判断当前节点的 key是否大于需要put的节点的key
        int cmp = x.key.compareTo(key);
        if (cmp > 0) {
            // 右递归
            x.left = put(x.left, key, value);
        } else if (cmp < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        return x;
    }


    // 查询树中指定key对应的value
    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x.value;
        }
        if (x.key.compareTo(key) > 0 && x.left != null) {
            return get(x.left, key);
        }
        if (x.key.compareTo(key) < 0 && x.right != null) {
            return get(x.right, key);
        }
        return null;
    }

    // 删除树中key对应的value
    public void delete(Key key) {
        root = delete(root, key);
    }

    // 删除指定树x中的key对应的value，并返回删除后的新树
    public Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            x.right = this.delete(x.right, key);
        } else if (cmp < 0) {
            x.left = this.delete(x.left, key);
        } else {
            if (x.left == null) {
                N--;
                return x.right;
            }
            if (x.right == null) {
                N--;
                return x.left;
            }
            // 找到删除节点的右子树的最左子树
            Node temp = x.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            Node n = x.right;
            while (n.left.left != null) {
                n = n.left;
            }
            temp.left = x.left;
            temp.right = x.right;
            // 让x节点的父节点指向temp
            x = temp;

            // 让元素个数-1
            N--;
        }
        return x;
    }

    // 找出整个树中最小的键
    public Key min() {
        return min(root).key;
    }

    // 找出指定树x中最小的键所在的节点
    private Node min(Node x) {
        if (x.left != null) {
            return min(x.left);
        } else {
            return x;
        }
    }

    // 找出整个树中最大的键
    public Key max() {
        return max(root).key;
    }

    // 找出指定树x中最大的键所在的节点
    private Node max(Node x) {
        if (x.right != null) {
            return max(x.right);
        } else {
            return x;
        }
    }

    // 指定树的前序遍历
    public void pre() {
        pre(root);
    }

    // 前序遍历指定树x
    private void pre(Node x) {
        if (x == null) {
            return;
        }
        System.out.println(x.key + "-" + x.value);
        if (x.left != null) {
            pre(x.left);
        }
        if (x.right != null) {
            pre(x.right);
        }
    }

    // 中序遍历
    public void mid() {
        mid(root);
    }

    private void mid(Node x) {
        if (x == null) {
            return;
        }
        mid(x.left);
        System.out.println(x);
        mid(x.right);
    }

    // 层序遍历
    public Queue<Key> layerErgodic() {
        // 定义两个队列，分别存储树中的key和node节点
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<Node>();
        nodes.enqueue(root);
        while (!nodes.isEmpty()) {
            // 从队列中弹出一个节点，把key放入keys中
            Node node = null;
            try {
                node = nodes.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            keys.enqueue(node.key);
            if (node.left != null) {
                nodes.enqueue(node.left);
            }
            if (node.right != null) {
                nodes.enqueue(node.right);
            }
        }
        return keys;
    }

    // 得到树的最大深度
    public int maxDepth() {
        return this.maxDepth(root);
    }

    // 得到树x的最大深度
    public int maxDepth(Node x) {
        if (x == null) {
            return 0;
        }
        int l = 0, r = 0, max = 0;
        if (x.left != null) {
            l = maxDepth(x.left);
        }
        if (x.right != null) {
            r = maxDepth(x.right);
        }
        return max = l > r ? l + 1 : r + 1;
    }

    public void test(String s, int num) {
        if (num == 0) {
            return;
        }
        test(s, num / 7);
        s += num % 7;
    }
}
