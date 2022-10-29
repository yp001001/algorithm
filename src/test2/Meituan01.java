package test2;

import java.util.*;

public class Meituan01 {

    public static void main(String[] args) {
        int store = store(new int[]{1, 4, 2, 4, 1, 3}, 10);
        System.out.println(store);
    }


    // 1 <= s <= 1000000
    // 子集不能有相同元素
    static int count = 0;
    static List<String> res = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    // 未剪枝
    public static void strCount(String s, int start) {
        if (start >= s.length()) {
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!sb.toString().contains(s.charAt(i) + "")) {
                sb.append(s.charAt(i));
                count++;
                strCount(s, i + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    //========================================
    static Map<String, Integer> map = new HashMap<>();

    public static int strCount_2(String s, int start, int n) {
        if (start >= n) {
            return 0;
        }
        if (map.containsKey(s.substring(start, n))) {
            return map.get(s.substring(start, n));
        }
        int res = 0;
        for (int i = start; i < n; i++) {
            res++;
            res += strCount_2(s, i + 1, n) % 202110101;
        }
        map.put(s.substring(start, n), res);
        return res;
    }

    //===========================小美的仓库=================================
    // 一段符合要求序列中的最长值（滑动窗口）
    // 如果有一辆车取不到本想取到的大米，小美会提前关闭仓库，这辆车以及后面的车辆都不再能进入仓库（此条件可以判断有负数也可以使用滑动窗口）
    public static int store(int[] cat, int m) {
        int l = 0, r = 0;
        int n = cat.length;
        int res = 0;
        int x = m;
        while (r < n) {
            if (x >= cat[r]) {
                x -= cat[r];
                r++;
            } else {
                res = Math.max(res, r - l + 1);
                l++;
                r = l;
                x = m;
            }
        }
        return res;
    }


    //============================组合===============
    public static void t03() {
        Map<Integer, Character> map = new HashMap<>();
        char c = 'a';
        for (int i = 0; i < 26; i++) {
            map.put(i, c);
            c++;
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 1000000; i++) {
            s.append(map.get(new Random().nextInt(26)));
        }

        char[] chars = s.toString().toCharArray();
        int[] charCnt = new int[26];

        // 统计每个字符出现的个数
        for (char aChar : chars) {
            charCnt[aChar - 'a']++;
        }

        int res = 1;
        for (int i = 0; i < 26; i++) {
            res *= (charCnt[i] + 1);
            res %= 20210101;
        }

        System.out.println(res);
    }
}
