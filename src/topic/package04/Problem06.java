package topic.package04;

import java.util.Stack;

public class Problem06 {

    // 2[a2[bc]]
    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray())
            if (c != ']') {
                stack.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                // 取出[]内的字符串
                while (!stack.isEmpty() && Character.isLetter(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                String sub = sb.toString();
                stack.pop();
                sb = new StringBuilder();
                // 获取倍数数字
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    sb.insert(0, stack.pop());
                }
                int count = Integer.parseInt(sb.toString());
                while (count > 0) {
                    for (char ch : sub.toCharArray()) {
                        stack.push(ch);
                    }
                    count--;
                }
            }

        StringBuilder retv = new StringBuilder();
        while (!stack.isEmpty()) {
            retv.insert(0, stack.pop());
        }
        return retv.toString();
    }


    public static void main(String[] args) {
        System.out.println(decodeString("3[a2[bc]]"));
        String a = "aaa";
        String b = "aaa";
        System.out.println(a == b);

//        要明白集合中的泛型是不可变的，但是数组里的类型是可变的
//        正是由于数组的这个先天特性，导致即使加入泛型，仍然无法在编译器预先检查出类型安全问题，假如数组允许使用泛型：
//        List<String> list = new ArrayList<>();
//        List<Object> obs = list; // 将String的集合赋值给Object的集合会报错，这句会编译报错
//        String[] strs = new String[4];
//        Object[] obs = strs;// 这句就不会报错

    }
}
