package test2;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 包含Min函数的栈
 * ----小顶堆----辅助栈保存最小值----自定义单链表添加MinValue
 */
class MinStack {

    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}