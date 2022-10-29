package com.yp.stack;

/**
 * 中缀表达式求值
 * 思路：
 * 1. 通过一个index（索引）来遍历我们的表达式
 * 2. 如果我们发现是一个数字，就直接入栈
 * 3. 如果发现是一个符号，分以下几种情况
 * 3.1 如果发现当前的符号栈为空，就直接入栈
 * 3.2 如果当前符号栈有操作符，就进行比较，如果当前的操作符小于或等于符号栈栈顶操作符
 * 则需要从数栈中pop出两个数，在符号栈中pop出一个符号，进行运算，将得到的结果入数栈，然后将当前操作符入符栈
 * 3.3 如果当前操作符优先级更高，就直接入栈
 * 4. 当表达式扫描完毕，就顺序从数栈和符号栈中pop出相应的数和符号，并运行
 * 5. 最后在数栈中就只有一个数字，就是表达式的结果
 */
public class Calculator {
    public static void main(String[] args) {

//         1. 定义一个数栈，一个操作符栈
        Stack2 numStack = new Stack2(20);
        Stack2 operStack = new Stack2(20);
        String str = "30+2*5/2+4+4/2+10";
        int answer = answer(numStack, operStack, str);
        System.out.println(answer);

    }


    public static int answer(Stack2 numStack, Stack2 operStack, String str) {
        int index = 0; // 索引
        while (true) {
            if (index == str.length()) {
                break;
            }
            char c = str.substring(index, index + 1).charAt(0);
            // 判断c是否是操作符
            if (operStack.isoper(c)) {
                if (operStack.isEmpty()) {
                    operStack.push(c);
                } else {
                    int priority = operStack.priority(c);
                    int look = operStack.priority(operStack.look());
                    if (priority > look) { //如果当前操作栈优先级更高，直接入栈
                        operStack.push(c);
                    } else {
                        int num1 = numStack.pop();
                        int num2 = numStack.pop();
                        int oper = operStack.pop();
                        int result = numStack.cal(num1, num2, oper);
                        operStack.push(c);
                        numStack.push(result);
                    }
                }
            } else {
                // 处理多位数的情况
                String s = c + "";
                while (true) {
                    if (index == str.length() - 1) {
                        break;
                    }
                    c = str.substring(++index, index + 1).charAt(0);
                    if (numStack.isoper(c)) {
                        index--;
                        break;
                    }
                    s = s + c;
                }
                numStack.push(Integer.parseInt(s));
            }
            index++;
        }
        // 将数据依次取出
        while (true) {
            if (numStack.getTop() == -1 || operStack.getTop() == -1) {
                return numStack.pop();
            }
            int num1 = numStack.pop();
            int num2 = numStack.pop();
            int pop = operStack.pop();
            numStack.push(numStack.cal(num1, num2, pop));
        }
    }
}


class Stack2 {
    private int maxSize;
    private int[] array;
    private int top = -1;

    public int getTop() {
        return top;
    }

    public Stack2(int maxSize) {
        this.maxSize = maxSize;
        this.array = new int[maxSize];
    }

    /**
     * 判断栈是否已满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 遍历栈
     */
    public void list() {
        for (int i = top; i >= 0; i--) {
            System.out.println(array[i] + "\t");
        }
    }

    /**
     * 入栈
     *
     * @param num
     */
    public void push(int num) {
        if (isFull()) {
            throw new RuntimeException("栈满");
        }
        array[++top] = num;
    }

    /**
     * 出栈
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        return array[top--];
    }

    /**
     * 得到操作符的优先级
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    public int look() {
        return array[top];
    }

    /**
     * 判断是否是一个字符
     *
     * @param num
     * @return
     */
    public boolean isoper(int num) {
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
    public int cal(int num1, int num2, int oper) {
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
}
