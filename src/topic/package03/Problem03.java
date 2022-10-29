package topic.package03;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem03 {

    // 最长不重复子串长度
    public static int lengthOfLongestSubstring1(String s) {
        int l = 0, r = 0;
        int max = 0;
        Set<Character> set = new HashSet<>();
        while (r < s.length()) {
            char c = s.charAt(r);
            if (set.contains(c)) {
                while (set.contains(c)) {
                    set.remove(s.charAt(l++));
                }
            }
            set.add(c);
            // 到了下一个
            r++;
            max = Math.max(max, r - l);
        }
        return max;
    }


    public static int lengthOfLongestSubstring2(String s) {
        if (s == null || "".equals(s)) return 0;
        char[] str = s.toCharArray();
        int N = str.length;
        int[] map = new int[256];
        Arrays.fill(map, -1);
        map[str[0]] = 0;
        int pre = 1;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            int p1 = i - map[str[i]];
            int p2 = pre + 1;
            pre = Math.min(p1, p2);
            ans = Math.max(ans, pre);
            map[str[i]] = i;
        }
        return ans;
    }
}
