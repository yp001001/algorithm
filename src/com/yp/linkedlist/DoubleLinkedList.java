package com.yp.linkedlist;

public class DoubleLinkedList {
    private Node head = new Node(0, "", "");

    /**
     * 遍历双向链表
     */
    public void list() {
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 尾插法
     *
     * @param node
     */
    public void add(Node node) {
        Node temp = head;
        if (temp.next == null) {
            temp.pre = null;
            temp.next = node;
            node.pre = temp;
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    /**
     * 删除指定的节点
     *
     * @param no
     */
    public void remove(int no) {
        // 判断 no 是否合法
        Node temp = head;
        while (temp.next != null) {
            if (no == temp.next.no) {
                if (temp.next.next == null) {
                    temp.next = null;
                    return;
                }
                temp.next = temp.next.next;
                temp.next.pre = temp;
            }
            temp = temp.next;
        }
    }
}


// 定义一个HeroNode
class Node {
    public int no;
    public String name;
    public String nickname;
    public Node next; // 指向下一个节点
    public Node pre;  // 指向前一个节点

    public Node(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return
                "no=" + no +
                        ", name='" + name + '\'' +
                        ", nickname='" + nickname + "\t";
    }
}