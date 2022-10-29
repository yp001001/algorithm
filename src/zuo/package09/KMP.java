package zuo.package09;

public class KMP {

    public static int getIndexOf(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) return -1;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[] next = getNextArray(str2);
        int i = 0, j = 0;
        while (i < str1.length && j < str2.length) {
            if (str1[i] == str2[j]) {
                i++;
                j++;
            } else if (next[j] == -1) { // j == 0;
                i++;
            } else {
                j = next[j];
            }
        }
        return j == str2.length ? i - j : -1;
    }


    public static int[] getNextArray(char[] str) {
        if (str.length == 1) return new int[]{-1};
        int[] next = new int[str.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < str.length) {
            if (str[i - 1] == str[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];  // 跑到下一个数
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

}
