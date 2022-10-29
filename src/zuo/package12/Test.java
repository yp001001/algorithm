package zuo.package12;

import static zuo.package01.Problem2.netherlandsFlag;

/**
 * 40亿个整数如何在1GB内存得到一个不存在的数
 */
public class Test {
    public static void main(String[] args) {
        int[] arr = new int[32];
        int i = 1;
        arr[i / 32] = arr[i / 32] | ((1 << (i % 32)));
        System.out.println(arr[1 / 32]);
        i = 32;
        arr[i / 32] = arr[i / 32] | ((1 << (i % 32)));
        System.out.println(arr[32 / 32]);
        i = 33;
        arr[i / 32] = arr[i / 32] | ((1 << (i % 32)));
        System.out.println(arr[33 / 32]);
    }

}
