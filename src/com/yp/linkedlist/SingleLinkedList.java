package com.yp.linkedlist;

import java.util.Stack;

public class SingleLinkedList {
    // 先初始化一个头节点，头节点不动，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 判断是否成为环形链表
     */

    /**
     * 添加元素
     *
     * @param node
     */
    public void add(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                temp.next = node;
                return;
            }
            temp = temp.next;
        }
    }

    /**
     * 删除链表
     * 注意：该链表不是双向链表，我们删除的时候，必须先找到需要删除的元素的前一个
     */
    public void remove(int num) {
        // 判断num的合法性
        int count = 0;
        HeroNode node = head;
        if (node.next == null) {
            throw new RuntimeException("链表为空~~~");
        }
        while (node.next != null) {
            if (count == num - 1) {
                node.next = node.next.next;
                return;
            }
            count++;
            node = node.next;
        }
    }


    /**
     * 修改链表数据，根据 HeroNode.no修改
     *
     * @param node
     */
    public void update(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                throw new RuntimeException("错误");
            }
            if (temp.next.no == node.no) {
                node.next = temp.next.next;
                temp.next = node;
                return;
            }
        }
    }

    /**
     * 遍历链表
     */
    public void list() {
        HeroNode temp = head.next;
        if (temp == null) {
            throw new RuntimeException("链表为空~~~");
        }
        while (temp != null) {
            System.out.print(temp + "\t");
            temp = temp.next;
        }
    }

    /**
     * 求单链表节点个数
     *
     * @return
     */
    public int count() {
        int count = 0;
        HeroNode temp = head.next;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    /**
     * 查找单链表中倒数第k个节点
     *
     * @param num
     * @return
     */
    public HeroNode get(int num) {
        num = count() - num;
        // 然后遍历得到需要的数据
        return null;
    }

    /**
     * 单链表的反转（会影响原链表）
     *
     * @return
     */
    public HeroNode reverse() {
        HeroNode reverse = new HeroNode(0, "", "");
        HeroNode temp = head.next;
        // 定义一个临时变量
        HeroNode node = null;
        while (temp != null) {
            node = temp.next;
            temp.next = reverse.next;
            reverse.next = temp;
            temp = node;
        }

        // 将头节点去掉
        reverse = reverse.next;

        return reverse;
    }

    /**
     * 单链表的合并，并且合并之后依然有序
     * head作为主链表，node作为附链表
     *
     * @param node
     */
    public void addAll(HeroNode node) {
        HeroNode temp = head; // 作为主链表的临时节点
        HeroNode temp2 = null;
        while (node != null && temp.next != null) {
            if (node.no == temp.next.no) {
                throw new RuntimeException("no存在重复");
            } else if (node.no < temp.next.no) {
                temp2 = node.next;
                node.next = temp.next;
                temp.next = node;
                node = temp2;
            }
            temp = temp.next;
        }
        if (temp2 != null) {
            temp.next = temp2;
        }
    }

    /**
     * 查找单链表的中间值（使用快慢指针）
     */
    public HeroNode midNode() {
        HeroNode slow = head.next;
        HeroNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast == null) {
            return null;
        }
        return slow;
    }

    /**
     * 查询是否成为环形链表（快慢指针）
     */
    public boolean isAnnulus() {
        if (head.next == null) {
            return false;
        }
        HeroNode slow = head.next;
        HeroNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 得到环形链表的入口（当快慢相遇时，重新定义一个与慢指针步长相同的指针temp，最后慢指针与temp相遇之处就是入口）
     */
    public HeroNode getAntrance() {
        if (head.next == null || isAnnulus() == false) {
            return null;
        }
        HeroNode slow = head.next;
        HeroNode fast = head.next;
        HeroNode temp = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        while (temp != slow) {
            temp = temp.next;
            slow = slow.next;
        }
        return temp;
    }


    /**
     * 逆序打印
     */
    public void reversePrint() {
        Stack<HeroNode> stack = new Stack<>();
        HeroNode node = head.next;
        while (node != null) {
            stack.push(node);
            node = node.next;
        }

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}


// 定义一个HeroNode
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; // 指向下一个节点

    public HeroNode(int no, String name, String nickname) {
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
