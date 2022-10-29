package everyday;


// https://leetcode.cn/problems/remove-duplicate-letters/
// 去除重复字符串，且使得去除后的字符串最小
public class Test50 {

    // 找出最小的满足 s[i] > s[i + 1]的下标，并去除字符 s[i]
    // 与单调栈类似
    public String removeDuplicateLetters(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!vis[c - 'a']) {         // 表示当前字符已经使用过，且后面字符都大于当前字符，我们直接丢弃后面与该字符相同的字符
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
                    if (num[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        // 后面还有该字符，我们先丢弃
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                sb.append(c);
                vis[c - 'a'] = true;
            }
            num[c - 'a']--;
        }
        return sb.toString();
    }

}
