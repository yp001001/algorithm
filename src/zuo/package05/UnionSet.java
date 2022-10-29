package zuo.package05;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class UnionSet<V> {

    public HashMap<V, Node<V>> nodes;
    public HashMap<Node<V>, Node<V>> parents;
    public HashMap<Node<V>, Integer> sizeMap;

    public static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    public UnionSet(List<V> values) {
        nodes = new HashMap<>();
        parents = new HashMap<>();
        sizeMap = new HashMap<>();
        for (V v : values) {
            Node<V> node = new Node(v);
            nodes.put(v, node);
            parents.put(node, node);
            sizeMap.put(node, 1);
        }
    }

    // 将沿途的节点全部设置为最顶层的祖先节点
    public Node<V> findFather(Node<V> cur) {
        Stack<Node<V>> stack = new Stack<>();
        while (cur != parents.get(cur)) {
            stack.push(cur);
            cur = parents.get(cur);
        }
        while (!stack.isEmpty()) {
            parents.put(stack.pop(), cur);
        }
        return cur;
    }

    public boolean isSameSet(V a, V b) {
        return findFather(nodes.get(a)) == findFather(nodes.get(b));
    }

    public void union(V a, V b) {
        Node<V> aHead = findFather(nodes.get(a));
        Node<V> bHead = findFather(nodes.get(b));
        if (aHead != bHead) {
            int aSize = sizeMap.get(aHead);
            int bSize = sizeMap.get(bHead);
            Node<V> smallHead = aSize > bSize ? bHead : aHead;
            Node<V> bigHead = smallHead == bHead ? aHead : bHead;
            parents.put(smallHead, bigHead);
            sizeMap.put(bigHead, aSize + bSize);
            sizeMap.remove(smallHead);
        }
    }

}
