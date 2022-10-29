package zuo.dp.problem;

import java.util.Stack;

public class Test02 {

    public static void main(String[] args) {
        Test02 test02 = new Test02();
        test02.minAddToMakeValid("()))))");
    }


    //=======================================使括号有效的最少添加============================

    public int minAddToMakeValid(String s) {
        if ("".equals(s)) return 0;
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i)) {
                stack.push('(');
            } else {
                if (stack.isEmpty()) {
                    ans++;
                } else {
                    stack.pop();
                }
            }
        }
        return ans + stack.size();
    }

    //=======================================平衡括号字符串的最少插入次数============================

    public int minInsertions(String s) {
        int ans = 0, need = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                need += 2;
                if (need % 2 == 1) {
                    ans++;
                    need--;
                }
            }
            if (s.charAt(i) == ')') {
                need--;
                if (need == -1) {
                    ans++;
                    need = 1;
                }
            }
        }
        return ans + need;
    }

    //=======================================最长有效括号============================

    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int n = s.length();
        // dp[i] 表示 s[0 - (i-1)]之间的最长有效括号
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '(') {
                stack.push(i);
            } else {
                if (!stack.isEmpty()) {
                    Integer low = stack.pop();
                    dp[i] = i - low + 1 + dp[low - 1];
                } else {
                    dp[i] = 0;
                }
            }
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(max, i);
        }
        return max;
    }


    //=======================================逆波兰表达式============================

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int a = stack.pop();
                int b = stack.pop();
                int camp = camp(a, b, token);
                stack.push(camp);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    int camp(int a, int b, String token) {
        switch (token) {
            case "+":
                return a + b;
            case "-":
                return b - a;
            case "*":
                return a * b;
            default:
                return b / a;
        }
    }
}
