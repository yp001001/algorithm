package com.test.string;

import java.util.Stack;

/*
有效的括号
 */
public class IsValid {
    public static void main(String[] args) {
        isValid("()");
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '{') {
                stack.push('}');
            } else if (c == ')') {
                stack.push(c);
            } else if (c == ']') {
                stack.push(c);
            } else {
                if (stack.pop() != c) {
                    return false;
                }
            }
        }
        return true;
    }
}