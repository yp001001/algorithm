package com.yp.stack;

public class ArrayStack {
    public static void main(String[] args) {
        Stack stack = new Stack(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.list();
        System.out.println(stack.pop());
        stack.list();
    }
}


class Stack {
    private int maxSize;
    private int[] array;
    private int top = -1;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 遍历栈
     */
    public void list() {
        for (int i = top; i >= 0; i--) {
            System.out.println(array[i] + "\t");
        }
    }

    /**
     * 入栈
     *
     * @param num
     */
    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("栈满");
        }
        array[++top] = num;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return array[top--];
    }
}