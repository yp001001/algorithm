package topic.package39;

// 来自百度
// 给定一个字符串str，和一个正数k
// str子序列的字符种数必须是k种，返回有多少子序列满足这个条件
// 已知str中都是小写字母
// 原始是取mod
// 本节在尝试上，最难的
// 搞出桶来，组合公式
public class SequenceKDifferentKinds {

    public static int nums(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        char[] str = s.toCharArray();
        int[] counts = new int[26];
        for (char c : str) {
            counts[c - 'a']++;
        }
        return f(counts, 0, k);
    }

    private static int f(int[] counts, int index, int rest) {
        if (index == counts.length) return 0;
        int p1 = f(counts, index + 1, rest);
        int p2 = 0;
        if (rest > 0) {
            p2 = ((1 << (counts[index]) - 1)) * f(counts, index + 1, rest - 1);
        }
        return p1 + p2;
    }
}
