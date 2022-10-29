package com.test.linkedlist;

import java.util.List;

/*
给你两个 非空 的链表，表示两个非负的整数。
它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字
 */
public class AddTwoNumbers {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(9);
        l1.add(9);
        l1.add(9);
        l1.add(9);
        l1.add(9);
        l1.add(9);
        l1.add(9);

        ListNode l2 = new ListNode(9);
        l2.add(9);
        l2.add(9);
        l2.add(9);

        ListNode listNode = addTwoNumbers(l1, l2);  //89990001


        while (listNode != null) {
            System.out.print(listNode.val);
            listNode = listNode.next;
        }

    }

    public static int Fib2(int n) {
        if (n == 0) {
            return 0;
        }
        return Fib(n - 1) + Fib(n - 2);
    }

    public static int Fib(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int d1 = 1;
        int d2 = 1;
        int sum = 0;
        for (int i = 2; i < n; i++) {
            sum = d1 + d2;
            d1 = d2;
            d2 = sum;
        }
        return sum;
    }

    public static ListNode addTwoNubers3(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        ListNode temp = node;
        int result;
        while (l1 != null && l2 != null) {
            result = l1.val + l2.val + temp.val;
            if (result > 9) {
                temp.val = result % 10;
                temp.next = new ListNode();
                temp = temp.next;
                temp.val = 1;
            } else {
                temp.val = result;
                temp.next = new ListNode();
                temp = temp.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            temp.val = l1.val + temp.val;
            if (temp.val > 9) {
                temp.val = temp.val % 10;
                temp.next = new ListNode();
                temp = temp.next;
                temp.val = 1;
            } else {
                temp.next = new ListNode();
                temp = temp.next;
            }

            l1 = l1.next;
        }

        while (l2 != null) {
            temp.val = l2.val + temp.val;
            if (temp.val > 9) {
                temp.val = temp.val % 10;
                temp.next = new ListNode();
                temp = temp.next;
                temp.val = 1;
            } else {
                temp.next = new ListNode();
                temp = temp.next;
            }
            l2 = l2.next;
        }

        if (temp.val == 0) {
            temp = node;
            while (temp.next != null) {
                if (temp.next.val == 0 && temp.next.next == null) {
                    temp.next = null;
                    break;
                }
                temp = temp.next;
            }
        }

        return node;
    }


    /*
      执行用时：2 ms,在所有 Java 提交中击败了96.00%的用户
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode();
        ListNode temp = node;
        int carry = 0; // 表示十位数
        while (l1 != null || l2 != null) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum > 9 ? 1 : 0; // 得到高一位数字
            sum = sum % 10;   // 得到该位数字
            temp.val = sum;
            temp.next = new ListNode(carry);
            temp = temp.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (temp.val == 0) {
            temp = node;
            while (temp.next != null) {
                if (temp.next.val == 0 && temp.next.next == null) {
                    temp.next = null;
                    break;
                }
                temp = temp.next;
            }
        }

        return node;
    }


    //执行用时：
    //9 ms, 在所有 Java 提交中击败了5.48%的用户
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode node = new ListNode();

        ListNode temp = node;
        String s;
        String substring;
        int result;

        while (l1 != null && l2 != null) {
            result = l1.val + l2.val + temp.val;
            if (result > 9) {
                s = result + "";
                substring = s.substring(1, 2);
                temp.val = Integer.parseInt(substring);
                temp.next = new ListNode(Integer.parseInt(s.substring(0, 1)));
            } else {
                temp.val = result;
                temp.next = new ListNode();
            }
            l1 = l1.next;
            l2 = l2.next;
            temp = temp.next;
        }
        while (l1 != null) {
            result = l1.val + temp.val;
            if (result > 9) {
                s = result + "";
                substring = s.substring(1, 2);
                temp.val = Integer.parseInt(substring);
                temp.next = new ListNode(1);
            } else {
                temp.val = result;
                if (l1.next != null) {
                    temp.next = new ListNode();
                }
            }
            l1 = l1.next;
            temp = temp.next;
        }


        while (l2 != null) {
            result = l2.val + temp.val;
            if (result > 9) {
                s = result + "";
                substring = s.substring(1, 2);
                temp.val = Integer.parseInt(substring);
                temp.next = new ListNode(1);
            } else {
                temp.val = result;
                if (l2.next != null) {
                    temp.next = new ListNode();
                }
            }
            l2 = l2.next;
            temp = temp.next;
        }
        temp = node;
        while (temp.next != null) {
            if (temp.next.val == 0 && temp.next.next == null) {
                temp.next = null;
                break;
            }
            temp = temp.next;
        }

        return node;
    }
}


//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    void add(int value) {
        ListNode temp = this;
        while (temp.next != null) {
            temp = temp.next;
        }
        ListNode node = new ListNode(value);
        temp.next = node;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }
}

