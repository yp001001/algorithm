package topic.package38;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.cn/problems/find-all-anagrams-in-a-string/
// 找到字符串中所有字母异位词
public class FindAnagrams {

    public static void main(String[] args) {
        List<Integer> list = findAnagrams("cbaebabacd", "abc");
        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    public static List<Integer> findAnagrams(String s, String p) {
        char[] source = s.toCharArray();
        char[] target = p.toCharArray();
        int need = 0;
        Set<Character> set = new HashSet<>();
        int[] str = new int[26];
        for (char c : target) {
            str[c - 'a']++;
            need++;
            set.add(c);
        }
        List<Integer> res = new ArrayList<>();
        int l = 0, r = 0;
        while (r < source.length) {
            int index = source[r] - 'a';
            if (set.contains(source[r])) {
                if (str[index] > 0) {
                    need--;
                }
            }
            str[index]--;
            if (r - l + 1 == target.length) {
                if (need == 0) {
                    res.add(l);
                    need++;
                }
                else {
                    if (set.contains(source[l])) {
                        if (str[source[l] - 'a'] >= 0) {
                            need++;
                        }
                    }
                }
                str[source[l] - 'a']++;
                l++;
            }
            r++;
        }
        return res;
    }

}
