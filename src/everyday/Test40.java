package everyday;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

// https://leetcode.cn/problems/validate-stack-sequences/
public class Test40 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        Deque<Integer> deque = new ArrayDeque<>();
        int index = 0;
        for (int i = 0; i < pushed.length; i++) {
            deque.push(pushed[i]);
            while (!deque.isEmpty() && deque.peek() == popped[index]) {
                deque.pop();
                index++;
            }
        }
        return deque.isEmpty();
    }
}
