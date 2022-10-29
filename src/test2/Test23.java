package test2;

import java.util.*;

public class Test23 {
    public static void main(String[] args) {
        Test23 test23 = new Test23();
        System.out.println(test23.cost(new int[]{1, 0, 2, 2, 1}));
    }

    public String char_and_num_return(String text_source) {
        // write code here
        String[] source = text_source.split("\\s+");
        StringBuilder sb = new StringBuilder();
        TreeSet<Integer> set = new TreeSet<>();
        int i = 0;
        while (i < source.length) {
            if (source[i].matches("-?[0-9]+\\.?[0-9]*")) {
                set.add(Integer.parseInt(source[i]));
            } else {
                sb.append(source[i] + " ");
            }
            i++;
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next() + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 成对的69
     *
     * @param S
     * @return
     */
    public String Paired69(String S) {
        if (S == null || "".equals(S)) {
            return null;
        }
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '6') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    // 如果栈为空，且前面数字匹配，后面一个数字为9，则须在第一个位置添加6
                    sb.insert(0, 6);
                } else {
                    stack.pop();
                }
            }
        }
        // 栈中剩余多少个6，我们就匹配多少个9
        sb.append(S);
        while (!stack.isEmpty()) {
            stack.pop();
            sb.append(9);
        }
        return sb.toString();
    }


    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char right = stack.pop();
                Character character = map.get(c);
                if (map.get(c) != right) return false;
            }
        }
        return stack.isEmpty();
    }


    public int cost(int[] array) {
        // write code here
        int n = array.length;
        int[] left = new int[n];
        Arrays.fill(left, 1);
        int[] right = new int[n];
        Arrays.fill(right, 1);
        for (int i = 1; i < n; i++) {
            if (array[i] > array[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (array[i] > array[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }
}
