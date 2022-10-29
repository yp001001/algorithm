package test2;

import java.util.*;

public class Test24 {

    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            entry.getValue();
        }
        String path = new Test24().simplifyPath(
                "/a//b////c/d//././/..");
        System.out.println(path);
    }

    public int minInsertions(String s) {
        int res = 0;
        // 需要的右括号
        int need = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                need += 2;
                // 表示此时需要的右括号为单数，()( 这种情况，我们需要在前面添加一个 )
                if (need % 2 == 1) {
                    res++;
                    need--;
                }
            } else {
                need--;
                // 此时的情况：）
                if (need == -1) {
                    res++;      // 添加右括号
                    need = 1;   // 添加了右括号，此时就需要一个左括号，need = 1
                }
            }
        }
        return res + need;
    }


    /**
     * 最长有效括号
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
                dp[i + 1] = 0;  // 该点为左括号，则到该括号一定不是有效括号
            } else {
                if (!stack.isEmpty()) {
                    int len = i - stack.pop() + 1;
                    dp[i + 1] = len + dp[i - len + 1];
                } else {
                    dp[i + 1] = 0;
                }
            }
        }
        // 寻找最大值
        int maxLength = 0;
        for (int i = 0; i <= n; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }


    /**
     * 简化路径
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Deque<String> deque = new ArrayDeque<>();
        String[] split = path.split("/");
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            if ("".equals(split[i])) {
                continue;
            }
            if (".".equals(split[i])) {
                continue;
            } else if ("..".equals(split[i])) {
                deque.pollLast();
            } else {
                deque.addLast(split[i]);
            }
        }
        if (deque.isEmpty()) return "/";
        while (!deque.isEmpty()) {
            ans.append("/" + deque.removeFirst());
        }
        return ans.toString();
    }
}


//    public int minInsertions(String s) {
//        int need = 0;
//        int n = s.length();
//        Stack<Character> stack = new Stack<>();
//        for (int i = 0; i < n; i++) {
//            char c = s.charAt(i);
//            if (c == '(') {
//                stack.push(c);
//            } else {
//                if (stack.isEmpty()) {
//                    if (i + 1 < n && s.charAt(i + 1) == ')') {
//                        need++;
//                        i += 1;
//                    } else {
//                        need += 2;
//                    }
//                } else {
//                    if (i + 1 < n && s.charAt(i + 1) == ')') {
//                        stack.pop();
//                        i += 1;
//                    } else {
//                        need++;
//                        stack.pop();
//                    }
//                }
//            }
//        }
//        return need + 2 * stack.size();
//    }

