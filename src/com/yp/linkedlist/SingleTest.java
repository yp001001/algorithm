package com.yp.linkedlist;

import org.junit.Test;

public class SingleTest {
    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.add(new HeroNode(1, "张三", "张三"));
        linkedList.add(new HeroNode(2, "李四", "李四"));
        linkedList.add(new HeroNode(4, "王五", "王五"));
        linkedList.add(new HeroNode(5, "王五2", "王五2"));
        linkedList.add(new HeroNode(6, "王五3", "王五3"));
        linkedList.add(new HeroNode(7, "王五4", "王五4"));
        HeroNode n1 = new HeroNode(8, "jack", "jack");
        HeroNode n2 = new HeroNode(9, "mark", "mark");
        n2.next = n1;
        linkedList.add(n1);
        linkedList.add(n2);
//        System.out.println(linkedList.midNode());
        System.out.println(linkedList.isAnnulus());
        System.out.println(linkedList.getAntrance());
    }
}
