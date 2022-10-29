package topic.package11;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.cn/problems/distinct-subsequences-ii/
public class UnSameStr {

    public static void main(String[] args) {
        System.out.println(distinctSubseqII("jguychjfhjvyfhccg"));
        System.out.println(distinctSubseqII_mod("jguychjfhjvyfhccg"));
        System.out.println(distinctSubseqII_back("jguychjfhjvyfhccg"));
    }

    public static int distinctSubseqII_mod(String s) {
        if (s == null || s.length() == 0) return 0;
        int mod = (int) 1e9 + 7;
        char[] str = s.toCharArray();
        int[] count = new int[26];
        int all = 1;
        for (char x : str) {
            int add = (all - count[x - 'a'] + mod) % mod;
            all = (all + add) % mod;
            count[x - 'a'] = (count[x - 'a'] + add) % mod;
        }
        return all - 1;
    }

    private static int distinctSubseqII(String s) {
        if (s == null || s.length() == 0) return 0;
        int all = 1;
        Map<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        for (char c : str) {
            int newAdd = all;
            all = newAdd + all - (map.containsKey(c) ? map.get(c) : 0);
            map.put(c, newAdd);
        }
        return all - 1;
    }

    static Set<String> set = new HashSet<>();

    public static int distinctSubseqII_back(String s) {
        if (s == null || s.length() == 0) return 0;
        StringBuilder sb = new StringBuilder();
        process(s.toCharArray(), 0, sb);
        return set.size() - 1;
    }

    private static void process(char[] str, int start, StringBuilder sb) {
        if (set.contains(sb.toString())) return;
        set.add(sb.toString());
        for (int i = start; i < str.length; i++) {
            sb.append(str[i]);
            process(str, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
