package niuke;

import java.util.Scanner;

public class Test06 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        int cn0 = 0, cn1 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') cn0++;
            else cn1++;
        }
        System.out.println(Math.max(fun(str, '0', cn0, cn1), fun(str, '1', cn0, cn1)));
    }

    private static int fun(String str, char ch, int cn0, int cn1) {
        int right = 0;
        int num = 0;
        int n = str.length();
        int start = str.indexOf(ch);
        int end = str.lastIndexOf(ch);
        if (ch == '1') num += (1 + cn1) * cn1 / 2;
        else num += (1 + cn0) * cn0 / 2;
        if (start > 0) {
            num += (1 + start) * start / 2;
        }
        if (right < n - 1) {
            int x = n - end - 1;
            num += (1 + x) * x / 2;
        }
        return num;
    }

    public static int process(char[] str, int index, int all, int preCost, int preVal) {
        if (index == str.length) {
            return all;
        }

        // 不使用该元素
        int p1 = process(str, index + 1, all, preCost, preVal);
        // 使用该元素
        int nowCost = str[index] == preVal ? preCost + 1 : 1;
        int p2 = process(str, index + 1, all + nowCost, nowCost, str[index]);
        return Math.max(p1, p2);
    }
}
