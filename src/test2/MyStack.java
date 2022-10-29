package test2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现一个栈
 */
public class MyStack {

    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        if (queue1.isEmpty()) {
            queue1.offer(x);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
        } else {
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
        }
    }

    public int pop() {
        if (queue1.isEmpty()) {
            if (queue2.isEmpty()) {
                return -1;
            }
            return queue2.poll();
        }
        return queue1.poll();
    }

    public int top() {
        if (queue1.isEmpty() && queue2.isEmpty()) return -1;
        return queue1.isEmpty() ? queue2.peek() : queue1.peek();
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}