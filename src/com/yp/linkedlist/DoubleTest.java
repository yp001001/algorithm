package com.yp.linkedlist;

public class DoubleTest {
    public static void main(String[] args) {
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(new Node(1,"张三","张三"));
        linkedList.add(new Node(2,"李四","李四"));
        linkedList.add(new Node(3,"王五","王五"));
        linkedList.list();
        linkedList.remove(3);
        System.out.println();
        linkedList.list();
    }
}
