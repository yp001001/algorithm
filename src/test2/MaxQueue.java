package test2;

import java.util.*;

public class MaxQueue {

    Deque<Integer> deque;
    Queue<Integer> queue;

    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        if (deque.isEmpty()) return -1;
        return deque.peekFirst();
    }

    public void push_back(int value) {
        queue.add(value);
        // deque保存的最大值在第一个位置，除掉的数据一定在它之前pop掉，而小于它的数据排在它之后
        while (!deque.isEmpty() && deque.peekLast() < value) {
            deque.removeLast();
        }
        deque.addLast(value);
    }

    public int pop_front() {
        if (!queue.isEmpty()) {
            Integer val = queue.poll();
            if (deque.peekFirst() == val.intValue()) {
                deque.removeFirst();
            }
            return val;
        }
        return -1;
    }
}
