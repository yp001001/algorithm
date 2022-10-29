package com.yp.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;


/**
 * 中缀表达式转后缀表达式
 * 思路：
 * 1. 初始化两个栈，运算符栈s1和存储中间结果的栈s2
 * 2. 从左至右扫描中缀表达式
 * 3. 遇到操作数栈时，将其压入s2
 * 4. 遇到运算符时，比较其与s1栈顶运算符的优先级
 * 4.1 如果s1为空，或栈顶元素为 ’(‘ ,则直接入栈
 * 4.2 若元素优先级高于栈顶元素，也将其压入栈s1
 * 4.3 若优先级低，将s1栈顶的运算符弹出压入到s2中，再次与s1新的栈顶元素比较
 * 5. 遇到括号时
 * 5.1 如果是左括号 ’(‘，则直接压入s1
 * 5.2 如果是右括号 ’)‘ 则依次弹出栈顶元素，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
 * 6. 重复步骤2-5，直到表达式的最左边
 * 7. 将s1中剩余的运算符依次弹出并压入s2
 * 8. 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
 */

/**
 * 后缀表达式 （逆波兰表达式）求值
 * 思路：
 * 从左至右扫描表达式，遇到数字时将数字压入堆栈，遇到运算符时
 * 弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素和栈顶元素），
 * 并将结果入栈，重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果
 */

// 逆波兰表达式计算器
public class PolandNotaion {
    public static void main(String[] args) {
        // 后缀表达式
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
        Stack<Integer> stack = new Stack();
        int answer = answer(stack, suffixExpression);
        System.out.println(answer);

        List<String> list = toInfixExpressionList("( 30 + 4 ) * 5 - 6");
        System.out.println(list);

        System.out.println("================方式2=================");
        List<String> list2 = tofixArray("(30+4)*5-6");
        List<String> list1 = toInfixExpressionList2(list2);
        System.out.println(list1);

    }

    public static int answer(Stack<Integer> stack, String suffixExpression) {
        int index = 0;
        String[] split = suffixExpression.split(" ");
        while (true) {
            if (index == split.length) {
                break;
            }
            String c = split[index];
            if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                Integer num1 = stack.pop();
                Integer num2 = stack.pop();
                int cal = cal(num1, num2, c.charAt(0));
                stack.push(cal);
            } else {
                stack.push(Integer.parseInt(c));
            }
            index++;
        }
        return stack.pop();
    }

    /**
     * 判断是否是一个字符
     *
     * @param num
     * @return
     */
    public static boolean isoper(int num) {
        if (num == '*' || num == '-' || num == '+' || num == '/') {
            return true;
        }
        return false;
    }


    /**
     * 计算方法
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public static int cal(int num1, int num2, int oper) {
        if (oper == '*') {
            return num1 * num2;
        } else if (oper == '/') {
            return num2 / num1;
        } else if (oper == '-') {
            return num2 - num1;
        } else {
            return num1 + num2;
        }
    }


    /**
     * 得到操作符的优先级
     *
     * @param oper
     * @return
     */
    public static Integer priority(String oper) {
        if (oper == "*" || oper == "/") {
            return 1;
        } else if (oper == "+" || oper == "-") {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param str
     * @return
     */
    public static List<String> toInfixExpressionList(String str) {
        Stack<String> numStack = new Stack();
        Stack<String> operStack = new Stack<>();
        String[] split = str.split(" ");
        int index = 0;
        while (true) {
            if (index == split.length) {
                break;
            }
            String s = split[index];
            if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s) || "(".equals(s) || ")".equals(s)) {
                if (operStack.isEmpty() || "(".equals(s) || ")".equals(s)) {
                    operStack.push(s);
                } else {
                    if (operStack.peek().equals("(")) {
                        operStack.push(s);
                    } else {
                        // 如果s为 ’)‘,则将运算符栈中数据依次push到数栈，直到遇到 ’)‘
                        if (s.equals(")")) {
                            while (true) {
                                String oper = operStack.pop();
                                if (oper.equals("(")) {
                                    break;
                                }
                                numStack.push(oper);
                            }
                        }
                        // 判断优先级
                        else {
                            Integer p1 = priority(s);
                            Integer p2 = priority(operStack.peek());
                            // s优先级高就压入栈
                            if (p1 > p2 || operStack.peek().equals("(")) {
                                operStack.push(s);
                            } else {
                                while (true) {
                                    if (operStack.isEmpty()) {
                                        operStack.push(s);
                                        break;
                                    } else {
                                        // 遇到左括号
                                        if (operStack.peek().equals("(")) {
                                            operStack.push(s);
                                            break;
                                        }
                                        // 遇到右括号
                                        else if (operStack.peek().equals(")")) {
                                            while (true) {
                                                operStack.pop(); //该行是 )
                                                if (operStack.isEmpty()) {
                                                    break;
                                                }
                                                String oper = operStack.pop();
                                                if (oper.equals("(")) {
                                                    operStack.push(s);
                                                    break;
                                                }
                                                numStack.push(oper);
                                            }
                                        } else {
                                            if (!operStack.isEmpty()) {
                                                numStack.push(operStack.pop());
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                numStack.push(s);
            }
            index++;
        }

        while (!operStack.isEmpty()) {
            numStack.push(operStack.pop());
        }

        List<String> list = new ArrayList<>();
        while (numStack.size() > 0) {
            list.add(numStack.pop());
        }
        Collections.reverse(list);
        return list;
    }

    /**
     * 将字符串转换成List集合
     *
     * @param array
     * @return
     */
    public static List<String> tofixArray(String array) {
        List<String> list = new ArrayList<>();
        char[] chars = array.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] < 48 || chars[i] > 57) {
                list.add(String.valueOf(chars[i]));
            } else {
                String s = String.valueOf(chars[i]);
                i++;
                // 加1可能到最后了
                if (i == chars.length) {
                    list.add(s);
                    break;
                }
                while (i < chars.length) {
                    if (chars[i] < 48 || chars[i] > 57) {
                        i--; // 将i复原
                        list.add(s);
                        s = null;
                        break;
                    } else {
                        s += chars[i];
                        i++;
                    }
                }
            }
        }
        return list;
    }


    // 中缀表达式转后缀表达式（2）
    public static List<String> toInfixExpressionList2(List<String> stringList) {
        Stack<String> stack = new Stack<>(); // 运算符栈
        // 因为操作数栈只有入栈没有出栈，我们可以使用List集合来处理
        List<String> list = new ArrayList<>();

        for (String s : stringList) {
            // 判断是否是数字
            if (s.matches("\\d+")) {
                list.add(s);
            } else {
                // 当栈空或栈顶元素为 ’(‘ 时
                if (stack.isEmpty() || stack.peek().equals("(") || s.equals("(")) {
                    stack.push(s);
                } else if (s.equals(")")) {
                    // 当s为 ’)‘ 时
                    while (!stack.peek().equals("(")) {
                        list.add(stack.pop());
                    }
                    // 将 ’(‘ 弹出
                    stack.pop();
                } else {
                    // 判断优先级 (优先级高，直接放入集合)
                    if (operation.getValue(s) > operation.getValue(stack.peek())) {
                        stack.push(s);
                    } else {
                        // 如果优先级低，将优先级高的放入集合，直到s的优先级更高
                        while (!stack.isEmpty() && operation.getValue(s) <= operation.getValue(stack.peek())) {
                            // 不需要判断是否为 ’）‘ 或’（‘
                            String pop = stack.pop();
                            list.add(pop);
                        }
                        // 最后将s保存到符号栈中
                        stack.push(s);
                    }
                }
            }
        }
        // 将剩余保存在栈中元素pop到集合中
        while (!stack.isEmpty()) {
            list.add(stack.pop());
        }
        return list;
    }
}


/**
 * 得到运算符的优先级
 */
class operation {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static int getValue(String oper) {
        switch (oper) {
            case "+":
                return ADD;
            case "-":
                return SUB;
            case "*":
                return MUL;
            case "/":
                return DIV;
            default:
                System.out.println("运算符错误");
                return 0;
        }
    }
}
