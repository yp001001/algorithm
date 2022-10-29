package test;

import com.yp2.tree.redblack.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU算法
 * <p>
 * 装箱是调用valueOf()方法
 */
public class LRU {



    public static void main(String[] args) {
        LRU lru = new LRU(3);
        lru.put(new LRU.Node<Integer, Integer>(1, 10));
        lru.put(new LRU.Node<Integer, Integer>(2, 20));
        lru.put(new LRU.Node<Integer, Integer>(3, 30));
        lru.get(1);
        lru.put(new LRU.Node<Integer, Integer>(4, 40));
        lru.doubleLinkedList.iterator();

    }


    private int cacheSize;
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRU(int cacheSize) {
        this.cacheSize = cacheSize;
        this.map = new HashMap<>();
        this.doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.remove(node);
        doubleLinkedList.addHeader(node);
        return node.v;
    }

    public void put(Node<Integer, Integer> n) {
        if (map.containsKey(n.k)) {
            Node<Integer, Integer> node = map.get(n.k);
            doubleLinkedList.remove(n);
            doubleLinkedList.addHeader(node);
        } else {
            if (cacheSize > map.size()) {
                map.put(n.k, n);
                doubleLinkedList.addHeader(n);
            } else {
                Node<Integer, Integer> node = doubleLinkedList.getLast();
                map.remove(node.k);
                map.put(n.k, n);
                doubleLinkedList.remove(node);
                doubleLinkedList.addHeader(n);
            }
        }
    }


    static class Node<K, V> {
        K k;
        V v;
        Node prev;
        Node next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.prev = this.next = null;
        }
    }

    class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        public void iterator() {
            Node<K, V> temp = head.next;
            while (temp != tail) {
                System.out.println(temp.v);
                temp = temp.next;
            }
        }

        /**
         * 添加到头节点
         */
        public void addHeader(Node<K, V> n) {
            n.next = head.next;
            head.next.prev = n;
            head.next = n;
            n.prev = head;
        }

        /**
         * 删除
         */
        public void remove(Node<K, V> n) {
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.next = null;
            n.prev = null;
        }

        /**
         * 获得最后一个节点
         */
        public Node<K, V> getLast() {
            return tail.prev;
        }
    }

}
