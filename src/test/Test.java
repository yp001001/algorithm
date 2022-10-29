package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ①：子类引用父类的静态成员变量不会导致子类的初始化
 * ②：通过数组定义来引用类，不会触发此类的初始化
 * ③：常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量类的初始化
 * ④：一个父接口并不会因为它的子接口或者实现类的初始化（<client>()方法）而初始化。只有当程序首次使用特定接口的静态字段时，才会导致该接口的初始化
 */
public class Test {

    static class Person {
        String name;
        Cat cat;

        public Person(String name, Cat cat) {
            this.name = name;
            this.cat = cat;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", cat=" + cat +
                    '}';
        }
    }

    static class Cat {
        String name;

        public Cat(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Cat cat = new Cat("小黄");
        Person person = new Person("张三", cat);
        System.out.println(person);
//        cat = new Cat("小白");
        cat.name = "小黑";
        System.out.println(person);


//        String s = "afgewfr";
//        char[] chars = s.toCharArray();
//        new String(chars, 0, 1);


//        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//        readWriteLock.readLock().lock();// 可降级不可升级
//        // int w = exclusiveCount(c); 获取低十六位，判断是否加写锁
//        readWriteLock.writeLock().lock();


//        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
////        queue.poll();
//        new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//                // put()方法调用了enqueue()方法，equeue()方法中调用了notEmpty.signal();所以可以将阻塞停止
//                queue.put(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
//        /**
//         * while (count == 0)
//         *     notEmpty.await();
//         */
//        Integer take = queue.take();
//        System.out.println(take);
    }


    /**
     * 判断是否是数字或字符
     *
     * @param c
     * @return
     */
    public boolean isOk(char c) {
        return c >= 48 && c <= 57 || c >= 65 && c <= 90 || c >= 97 && c <= 122;
    }

    private boolean eq(char a, char b) {
        if (a <= 57) {
            return a == b;
        }
        return a == b || a + 32 == b || b + 32 == a;
    }

    public boolean isPalindrome(String s) {

        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            while (!isOk(s.charAt(l)) && l < r) {
                l++;
            }
            while (!isOk(s.charAt(r)) && l < r) {
                r--;
            }
            if (!eq(s.charAt(l++), s.charAt(r--))) {
                return false;
            }
        }
        return true;
    }


    //======================


    public boolean isPalindrome(ListNode head) {
        // 使用快慢指针，快指针到底部反转慢指针
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = slowFast(head);
        slow = reverse(slow.next);
        ListNode temp = head;
        while (slow != null) {
            if (temp.val != slow.val) {
                return false;
            }
            slow = slow.next;
            temp = temp.next;
        }
        return true;

    }

    private ListNode slowFast(ListNode head) {
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 反转链表
    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        ListNode temp = node;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        return prev;
    }
}


interface A {
    int x = 1;
}

interface B extends A {
    int y = 2;
}


class SuperClass {
    static int value = 234;

    static {
        System.out.println("superClass");
    }
}

class SonClass extends SuperClass {
    static {
        System.out.println("SonClass");
    }

    static int values = 123;
}

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

