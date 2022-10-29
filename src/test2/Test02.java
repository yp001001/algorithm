package test2;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Test02 {
    public static void main(String[] args) {
        Test02 test02 = new Test02();
    }


    Map<Node, Node> map = new HashMap<>();

    /**
     * 方式一：使用 Map + 递归
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        if (!map.containsKey(head)) {
            Node node = new Node(head.val);
            map.put(head, node);
            node.next = copyRandomList(head.next);
            node.random = copyRandomList(head.random);
            return node;
        }
        return map.get(head);
    }

    public Node copyRandomList_2(Node head) {
        if (head == null) {
            return head;
        }
        //将拷贝节点放到原节点后面，例如1->2->3这样的链表就变成了这样1->1'->2->2'->3->3'
        for (Node node = head, copy = null; node != null; node = node.next.next) {
            copy = new Node(node.val);
            copy.next = node.next;
            node.next = copy;
        }
        //把拷贝节点的random指针安排上
        for (Node node = head; node != null; node = node.next.next) {
            if (node.random != null) {
                node.next.random = node.random.next;
            }
        }
        //分离拷贝节点和原节点，变成1->2->3和1'->2'->3'两个链表，后者就是答案
        Node newHead = head.next;
        for (Node node = head, temp = null; node != null && node.next != null; ) {
            temp = node.next;
            node.next = temp.next;
            node = temp;
        }
        return newHead;
    }

    public Node copyRandomList_3(Node head) {
        if (head == null) {
            return null;
        }
        Node copy;
        Node temp = head;
        // 1-2-3 => 1-1-2-2-3-3
        while (temp != null) {
            copy = new Node(temp.val);
            copy.next = temp.next;
            temp.next = copy;
            temp = temp.next.next;
        }
        // 将random添加
        temp = head;
        while (temp != null && temp.next != null) {
            if (temp.random != null) temp.next.random = temp.random.next;
            temp = temp.next.next;
        }
        // 分离
        Node newHead = head.next;
        for (Node node = head, t = null; node != null && node.next != null; ) {
            t = node.next;
            node.next = t.next;
            node = t;
        }
        return newHead;
    }
//Next pointer of node with label 7 from the original list was modified.
}
