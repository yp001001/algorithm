package test2;

import java.util.Hashtable;
import java.util.Stack;

/**
 * 两个栈实现一个队列
 * ***一个只负责取，一个只负责拿
 */
public class CQueue {

    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put(null,1);
    }

    Stack<Integer> s1;
    Stack<Integer> s2;

    public CQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void appendTail(int value) {
        s1.push(value);
    }

    public int deleteHead() {
        if (!s2.isEmpty()) {
            return s2.pop();
        }
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return s2.isEmpty() ? -1 : s2.pop();
    }
}
