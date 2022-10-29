package com.test.linkedlist;

import java.util.HashMap;
import java.util.Map;


/*
LRU算法
 */
public class LRUCache {

    class Node<K, V> {
        K k;
        V v;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
            this.prev = this.next = null;
        }
    }

    /*
    构建一个双向链表，存放我们的Node
     */
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
            while (temp != null) {
                System.out.println(temp.v);
                temp = temp.next;
            }
        }

        /*
        添加到头结点
         */
        public void addHead(Node<K, V> node) {
            node.next = head.next;
            head.next.prev = node;
            node.prev = head;
            head.next = node;
        }

        /*
        删除节点
         */
        public void remove(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.next = null;
            node.prev = null;
        }

        /*
        获得最后一个节点
         */
        public Node getLast() {
            return tail.prev;
        }
    }

    private int chacheSize;
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRUCache(int chacheSize) {
        this.chacheSize = chacheSize;
        this.map = new HashMap<>();
        this.doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.remove(node);
        doubleLinkedList.addHead(node);
        return node.v;
    }


    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node<Integer, Integer> node = map.get(key);
            node.v = value;
            doubleLinkedList.remove(node);
            doubleLinkedList.addHead(node);
        } else {
            Node<Integer, Integer> node = new Node<>(key, value);
            if (chacheSize > map.size()) {
                map.put(key, node);
                doubleLinkedList.addHead(node);
            } else {
                Node last = doubleLinkedList.getLast();
                doubleLinkedList.remove(last);
                doubleLinkedList.addHead(node);
                map.remove(last.k);
                map.put(key, node);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4,4);
    }
}
