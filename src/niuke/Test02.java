package niuke;

import java.util.*;

/**
 * 拼多多笔试题
 */
public class Test02 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        PriorityQueue<String> queue = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('a');
        }
        for (int i = 0; i < m; i++) {
            sb.append('b');
        }
        marked = new boolean[m + n];
        String str = sb.toString();
        sb = new StringBuilder();
        backtrack(str, sb,0);
//        Collections.sort(res);
        System.out.println(res.get(k));
    }

    static List<String> res = new ArrayList<>();
    static boolean[] marked;

    public static void backtrack(String str, StringBuilder sb,int start) {
        res.add(sb.toString());
        for (int i = 0; i < str.length(); i++) {
            // 只取前面的，marked[i - 1]
            if (marked[i] || (i > 0 && str.charAt(i - 1) == str.charAt(i) && !marked[i - 1])) continue;
            sb.append(str.charAt(i));
            marked[i] = true;
            backtrack(str, sb,start + 1);
            marked[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int sz = 0;
            int num = nums[i];
            while (num > 0) {
                sz++;
                num /= 2;
            }
            System.out.println(sz);
        }
    }
}
