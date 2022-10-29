package topic.package19;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllConcatenatedWordsInADict {


    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        if (words == null || words.length < 3) return null;
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        List<String> res = new ArrayList<>();
        TrieNode trieNode = new TrieNode();
        for (String word : words) {
            Arrays.fill(dp, 0, word.length(), 0);
            if (word.length() > 0 && split(word.toCharArray(), trieNode, 0)) {
                res.add(word);
            } else {
                TrieNode temp = trieNode;
                trieNode.insert(temp, word.toCharArray());
            }
        }
        return res;
    }

    static int[] dp = new int[1000];

    public static boolean split_dp(char[] s, TrieNode r, int i) {
        if (dp[i] != 0) {
            return dp[i] == 1;
        }
        boolean res = false;
        if (i == s.length) res = true;
        else {
            int path;
            TrieNode c = r;
            for (int end = i; end < s.length; end++) {
                path = s[end] - 'a';
                if (c.nexts[path] == null) break;
                c = c.nexts[path];
                if (c.end && split(s, r, end + 1)) {
                    res = true;
                    break;
                }
            }
        }
        dp[i] = res ? 1 : -1;
        return res;
    }

    public static boolean split(char[] s, TrieNode r, int i) {
        boolean res = false;
        if (i == s.length) res = true;
        else {
            int path;
            TrieNode c = r;
            for (int end = i; end < s.length; end++) {
                path = s[end] - 'a';
                if (c.nexts[path] == null) break;
                c = c.nexts[path];
                if (c.end && split(s, r, end + 1)) {
                    res = true;
                    break;
                }
            }
        }
        return res;
    }


    class TrieNode {
        public boolean end;
        public TrieNode[] nexts;

        public TrieNode() {
            end = false;
            nexts = new TrieNode[26];
        }

        public void insert(TrieNode root, char[] s) {
            int path;
            for (char c : s) {
                path = c - 'a';
                if (root.nexts[path] == null) {
                    root.nexts[path] = new TrieNode();
                }
                root = root.nexts[path];
            }
            root.end = true;
        }
    }
}
