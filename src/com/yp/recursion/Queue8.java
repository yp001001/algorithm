package com.yp.recursion;

public class Queue8 {
    public static int max = 8;
    public static int array[] = new int[8];
    static int count = 0;

    public static void main(String[] args) {
        Queue8.check(0);
        System.out.println("==============================");
        System.out.println("count:" + count);
    }


    public static void check(int num) {
        // 表示找到一种方法
        if (num == max) {
            count++;
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            array[num] = i;  // 放置位置
            if (clash(num, array)) {   // 表示不冲突，寻找下一个存放点
                check(num + 1);
            }
        }
    }


    /**
     * 打印数组
     */
    public static void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "\t");
        }
        System.out.println();
    }

    /**
     * 查看是否冲突
     * false表示冲突
     *
     * @param n
     * @param array
     * @return
     */
    public static boolean clash(int n, int array[]) {
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(array[i] - array[n]) == Math.abs(i - n)) {
                return false;
            }
        }
        return true;
    }


}
