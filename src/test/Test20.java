package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Random 的随机原理是对一个”随机种子”进行固定的算术和位运算，得到随机结果，
 * 再使用这个结果作为下一次随机的种子。在解决线程安全问题时，
 * Random 使用 CAS 更新下一次随机的种子，可以想到，如果多个线程同时使用这个对象，
 * 就肯定会有一些线程执行 CAS 连续失败，进而导致线程阻塞。
 */
public class Test20 {

    /**
     * 每个线程都有
     */
    ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int idx = new Random().nextInt(1);
            System.out.println(idx);
        }
//        List<Integer> list = new ArrayList<>();
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        list.remove(2);     // 默认删的是下标
//        System.out.println(list);
    }

    private static void test() {
        Node node = new Node(3);
        node.next = new Node(3);
        node.next.next = new Node(3);
        node.next.next.next = node;
        Test20 test20 = new Test20();
        test20.insert(node, 2);
    }

    private static void lianbiao() {
        Test20 test20 = new Test20();
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode res = test20.addTwoNumbers(l1, l2);
    }

    /**
     * 排序的循环链表
     *
     * @param head
     * @param insertVal
     * @return
     */
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node node = head.next;
        Node max = head;
        while (node != null && max != node) {
            // 找到最大的节点
            max = max.val > node.val ? max : node;
            // 防止 [1,3,3] 这种情况
            if (max.next == head) {
                break;
            }
            node = node.next;
        }
        if (insertVal > max.val || insertVal <= max.next.val) {
            Node t = new Node(insertVal);
            t.next = max.next;
            max.next = t;
            return head;
        }
        node = head;
        while (true) {
            if (node.val <= insertVal && node.next.val >= insertVal) {
                Node n = new Node(insertVal);
                n.next = node.next;
                node.next = n;
                return head;
            }
            node = node.next;
        }
    }

    /**
     * 重排链表
     * 寻找链表中点 + 链表逆序 + 合并链表
     *
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode temp = head;
        List<ListNode> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp);
            temp = temp.next;
        }
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) break;
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    /**
     * 链表中的两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode res = new ListNode();
        ListNode temp = res;
        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            temp.val = v1 + v2;
            temp.next = new ListNode();
            temp = temp.next;
            l1 = l1 == null ? l1 : l1.next;
            l2 = l2 == null ? l2 : l2.next;

        }
        return reverseList(res);
    }

    public ListNode reverseList(ListNode head) {
        ListNode node = new ListNode();
        ListNode res = node;
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = res.next;
            res.next = temp;
            temp = next;
        }
        return node.next;
    }


    /**
     * 两个链表的第一个重合节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;
        while (t1 != t2) {
            t1 = t1 == null ? headB : t1.next;
            t2 = t2 == null ? headA : t2.next;
        }
        return t1;
    }

    /**
     * 回文串的最多个数（中心拓展法）
     *
     * @param s
     * @return
     */

    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n * 2 - 1; i++) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l--) == s.charAt(r++)) ans++;
        }
        return ans;
    }


    /**
     * 最多减少一个字符，判断该字符串是否是回文串
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return isValid(s, i + 1, j) || isValid(s, i, j + 1);
            }
        }
        return true;
    }

    private boolean isValid(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}


class Node {
    public int val;
    public Node next;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }
}