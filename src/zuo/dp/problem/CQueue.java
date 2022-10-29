package zuo.dp.problem;

import java.util.Stack;

public class CQueue {
    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public CQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        in2out();
        return outStack.pop();
    }

    public int peek() {
        in2out();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    void in2out() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
