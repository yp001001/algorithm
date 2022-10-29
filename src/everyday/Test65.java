package everyday;

import java.util.ArrayList;
import java.util.List;

public class Test65 {

    public static void main(String[] args) {
        System.out.println(isFlipedString_2("waterbottle", "erbottlewat"));
    }

    public static boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        List<Integer> list = new ArrayList<>();
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n = str1.length;
        for (int i = 0; i < n; i++) {
            if (str2[0] == str1[i])
                list.add(i);
        }
        for (int i : list) {
            int size = 0;
            boolean flag = true;
            while (size < n) {
                if (str2[size++] != str1[i++ % n]) {
                    flag = false;
                    break;
                }
            }
            if (flag) return true;
        }
        return false;
    }

    static boolean isFlipedString_2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        return (s2 + s2).contains(s1);
    }

}
