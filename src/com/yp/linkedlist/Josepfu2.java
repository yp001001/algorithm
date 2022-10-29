package com.yp.linkedlist;

/*
约瑟夫问题
 */
public class Josepfu2 {
    private Girl first;

    /**
     * 解决约瑟夫问题
     */
    public void countBoy(int step) {
        // 1. 将Last指针移动到最后
        Girl last = first;
        while (last.next != first) {
            last = last.next;
        }
        while (first != last) {
            for (int i = 1; i < step; i++) {
                first = first.next;
                last = last.next;
            }
            System.out.println(first);
            first = first.next;
            last.next = first;
        }
        System.out.println(first);
    }


    public void add(int num) {
        if (num < 1) {
            System.out.println("输入数值不合法");
            return;
        }
        Girl last = null;
        for (int i = 1; i <= num; i++) {
            if (i == 1) {
                Girl girl = new Girl(i);
                first = girl;
                first.next = first;
                last = first.next;
                continue;
            }
            Girl girl = new Girl(i);
            last.next = girl;
            girl.next = first;
            last = girl;
        }

    }

    /**
     * 遍历元素
     */
    public void list() {
        Girl temp = first;
        while (temp.next != first) {
            System.out.println(temp);
            temp = temp.next;
        }
        // 将最后一个元素打印出来
        System.out.println(temp);
    }


}


class Girl {
    int no;
    Girl next;

    public Girl(int no) {
        this.no = no;
    }


    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
