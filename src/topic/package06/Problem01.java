package topic.package06;

import java.util.LinkedList;

public class Problem01 {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }

    // 0):负责的这一段的结果是多少
    // 1):负责的这一段计算到了哪个位置
    private static int[] f(char[] str, int i) {
        LinkedList<String> stack = new LinkedList<>();
        int cur = 0;
        int[] arr = null;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '(') {         // 遇到符号，将之前的解决
                addNum(stack, cur);
                stack.addLast(String.valueOf(str[i++]));
                cur = 0;
            } else {
                arr = f(str, i + 1);
                cur = arr[0];
                i = arr[1] + 1;
            }
        }
        // 将最后没有放进栈的数放入计算
        addNum(stack, cur);
        int num = getNumber(stack);
        return new int[]{num, i};
    }

    private static int getNumber(LinkedList<String> stack) {
        String cur;
        int res = 0;
        int num;
        boolean add = true;
        while (!stack.isEmpty()) {
            cur = stack.removeFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.parseInt(cur);
                res += add ? num : -num;
            }
        }
        return res;
    }

    private static void addNum(LinkedList<String> stack, int cur) {
        if (!stack.isEmpty()) {
            String top = stack.removeLast();
            if (top.equals("+") || top.equals("-")) {
                stack.addLast(top);
            } else {
                int num = Integer.parseInt(stack.removeLast());
                cur = top.equals("*") ? num * cur : num / cur;
            }
        }
        stack.addLast(String.valueOf(cur));
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+41*(2-1)"));
    }


}
