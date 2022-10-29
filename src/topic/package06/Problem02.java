package topic.package06;


import java.util.Stack;

public class Problem02 {

    public static String decodeString(String s) {
        if (s == null || "".equals(s)) return null;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c != ']') {
                stack.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                // 将字符串保存到sb中
                while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                // 将 '[' 删掉
                stack.pop();
                String sub = sb.toString();
                sb = new StringBuilder();
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                int num = Integer.parseInt(sb.toString());
                for (int i = 0; i < num; i++) {
                    for (char c1 : sub.toCharArray()) {
                        stack.push(c1);
                    }
                }
            }
        }
        StringBuilder retv = new StringBuilder();
        while (!stack.isEmpty()) {
            retv.insert(0, stack.pop());
        }
        return retv.toString();
    }
}
