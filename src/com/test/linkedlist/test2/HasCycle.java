package com.test.linkedlist.test2;

public class HasCycle {

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(6);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next.next = new ListNode(6);
        removeElements(node, 6);
    }


    /*
    存在一个按升序排列的链表，给你这个链表的头节点 head
    请你删除所有重复的元素，使每个元素 只出现一次 。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while (temp.next != null) {
            if (temp.val == temp.next.val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }


    // 反转链表
    public ListNode reverseList(ListNode head) {
        ListNode node = new ListNode(0);
        ListNode temp = head;
        ListNode flag = null;
        while (temp != null) {
            flag = temp.next;
            temp.next = node.next;
            node.next = temp;
            temp = flag;
        }
        return node.next;
    }


    //    给你一个链表的头节点 head 和一个整数 val ，
    //    请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
    public static ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode temp = node;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return node.next;
    }


    // 判断是否为环形链表
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }


    // 合并两个有序链表
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(0);
        ListNode temp = node;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                temp.next = l2;
                temp = temp.next;
                l2 = l2.next;
            } else {
                temp.next = l1;
                l1 = l1.next;
                temp = temp.next;
            }
        }

        if (l1 == null) {
            temp.next = l2;
        } else {
            temp.next = l1;
        }

        return node.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

