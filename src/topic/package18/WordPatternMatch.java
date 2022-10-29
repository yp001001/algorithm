package topic.package18;

import java.util.HashSet;

// 单词规律二
public class WordPatternMatch {

    public static void main(String[] args) {
        System.out.println(wordPatternMatch("aba", "abcaabc"));
    }

    public static boolean wordPatternMatch(String pattern, String str) {
        return match(str, pattern, 0, 0, new String[26], new HashSet());
    }

    private static boolean match(String str, String pattern, int si, int pi, String[] map, HashSet<String> set) {
        if (si == str.length() && pi == pattern.length()) return true;
        if (si == str.length() || pi == pattern.length()) return false;
        int ch = pattern.charAt(pi) - 'a';
        String cur = map[ch];
        // 如果该字符已经指定
        if (cur != null) {
            return si + cur.length() <= str.length()
                    && cur.equals(str.substring(si, si + cur.length()))
                    && match(str, pattern, si + cur.length(), pi + 1, map, set);
        }
        // 该字符没有指定
        for (int i = si; i < str.length(); i++) {
            String s = str.substring(si, i + 1);
            if (!set.contains(s)) {
                set.add(s);
                map[ch] = s;
                if (match(str, pattern, i + 1, pi + 1, map, set)) {
                    return true;
                }
                set.remove(s);
                map[ch] = null;
            }
        }
        return false;
    }

}
