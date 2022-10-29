package com.test.string;

import java.util.regex.Pattern;

public class ReverseWithNum {
    public static void main(String[] args) {
        int i = 100;
        System.out.println(i % 10);
        System.out.println(i % 10 / 10);
    }

    public static int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev > Integer.MAX_VALUE / 10 || rev < Integer.MIN_VALUE / 10) {
                return 0;
            }
            int digst = x % 10;      // 得到个位数
            x /= 10;                 // 将x除10
            rev = rev * 10 + digst;  // 将原来的数乘10+digst
        }
        return rev;
    }


    /*
    带符号的数字反转
     */
    public static int reverse(int x) {
        String s = x + "";
        int length = s.length();
        char[] chars = new char[length];
//        boolean flag = Pattern.matches("\\d", s.charAt(0) + "");
        String s1 = s.charAt(0) + "";
        boolean flag = s1.equals("-") || s1.equals("+");
        if (!flag) {
            int n = 0;
            for (int i = length - 1; i >= 0; i--) {
                chars[n] = s.charAt(i);
                n++;
            }
        } else {
            int n = 1;
            for (int i = length - 1; i > 0; i--) {
                chars[n] = s.charAt(i);
                n++;
            }
            chars[0] = s.charAt(0);
        }
        String str = new String(chars);
        System.out.println(str);
        long i = Long.parseLong(str);
        if (i > Integer.MAX_VALUE || i < Integer.MIN_VALUE) {
            return 0;
        }
        return Integer.parseInt(i + "");
    }
}
