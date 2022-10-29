package everyday;


import java.util.*;


// https://leetcode.cn/problems/orderly-queue/
public class Test12 {


    public static void main(String[] args) {
        String arr = "bwrhtr";
        int k = 4;
        String s1 = orderlyQueue(arr, k);
        String s2 = orderlyQueue2(arr, k);
        if (!s1.equals(s2)) {
            System.out.println("oops");
        }
    }

    static TreeSet<String> set = new TreeSet<>();

    // 不行，非常烂
    public static String orderlyQueue(String s, int k) {
        if (s == null || s.length() <= 1 || k == 0) return s;
        process(s.toCharArray(), k);
        char[] array = s.toCharArray();
        Arrays.sort(array);
        String ceiling = set.ceiling(new String(array));
        return ceiling;
    }

    private static void process(char[] str, int k) {
        String s = new String(str);
        if (set.contains(s)) return;
        else set.add(s);
        for (int i = 0; i < k; i++) {
            char[] newStr = swap(str, i);
            process(newStr, k);
        }
    }

    public static char[] swap(char[] str, int k) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        char c = sb.charAt(k);
        sb.deleteCharAt(k);
        sb.append(c);
        return sb.toString().toCharArray();
    }


    // k > 1 可以实现冒泡
    public static String orderlyQueue2(String s, int k) {
        if (k == 1) {
            String smallest = s;
            StringBuilder sb = new StringBuilder(s);
            int n = s.length();
            for (int i = 1; i < n; i++) {
                char c = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(c);
                if (sb.toString().compareTo(smallest) < 0) {
                    smallest = sb.toString();
                }
            }
            return smallest;
        } else {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }
    }

}
