package com.yp.linkedlist;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(5);
        list.list();
        list.countBoy(2,2,5);
    }
}


class CircleSingleLinkedList {
    // 创建一个first节点
    private Boy first = null;

    /**
     * 添加元素
     *
     * @param num
     */
    public void add(int num) {
        if (num < 1) {
            System.out.println("输入的数值不合法！！！");
            return;
        }
        // 创建辅助指针指向最后一个节点
        Boy last = null;
        for (int i = 1; i <= num; i++) {
            if (i == 1) {
                Boy boy = new Boy(i);
                first = boy;
                // 将next节点指向自己
                first.next = first;
                last = first.next;
                continue;
            }
            Boy temp = new Boy(i);
            // last的下一个节点指向temp
            last.next = temp;
            // 将最后一个节点的next指向头
            temp.next = first;
            // 将last指向最后一个节点
            last = temp;
        }
    }

    /**
     * 遍历元素
     */
    public void list() {
        Boy temp = first;
        while (temp.next != first) {
            System.out.println(temp);
            temp = temp.next;
        }
        // 将最后一个元素打印出来
        System.out.println(temp);
    }

    /**
     * 解决约瑟夫问题
     *
     * @param startNo  表示从第几个小孩开始数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少个小孩
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (first == null || countNum > nums || nums < 1 || startNo > nums) {
            System.out.println("输入参数有误");
            return;
        }
        /**
         * 思路：
         *  1. 创建一个新指针指向first的前一个节点
         *  2. 将头节点和新指针转移到开始数小孩的地址
         *  3. 循环数数
         *  4. first = first.next, cur.next = first;
         */
        // 1. 创建一个指针，将其放置在链表尾部
        Boy cur = first;
        while (true) {
            if (cur.next == first) {
                break;
            }
            cur = cur.next;
        }
        // 2. 将cur和first同时移动到指定的位置
        for (int i = 0; i < startNo - 1; i++) {
            first = first.next;
            cur = cur.next;
        }
        while (true) {
            // 表示已经到最后一个小孩了
            if (cur == first) {
                System.out.print(first + "\t");
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.next;
                cur = cur.next;
            }
            System.out.print(first + "\t");
            // 将已经到达的节点移除
            first = first.next;
            cur.next = first;
        }
    }
}


class Boy {
    private int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}