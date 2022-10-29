package zuo.package09;

import java.util.Comparator;

/* 马拉车算法 */
public class Manacher {

    public static String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append("#" + s.charAt(i));
        }
        sb.append("#");
        String str = sb.toString();
        int len = 1;
        int size = str.length();
        int start = 0, end = 0;
        for (int i = 0; i < size; i++) {
            int l = i - 1, r = i + 1;
            boolean flag = false;
            while (l >= 0 && r < size) {
                if (str.charAt(l) != str.charAt(r)) break;
                flag = true;
                l--;
                r++;
            }
            if (flag && len < r - l - 1) {
                len = r - l - 1;
                start = l + 1;
                end = r - 1;
            }
        }
        return s.substring(start / 2, end / 2);
    }

    public static void main(String[] args) {
        String cccc = longestPalindrome("cbbd");
        System.out.println(cccc);
    }
}

