package casual;

import java.util.Arrays;
import java.util.List;

// https://leetcode.cn/problems/UhWRSj/
public class Test03 {

    public static void main(String[] args) {
        Test03 test03 = new Test03();
        List<String> list = Arrays.asList("cat", "bat", "rat");
        System.out.println(test03.replaceWords(list, "the cattle was rattled by the battery"));
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            add(trie, word);
        }
        String[] words = sentence.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(findPrefix(word, trie) + " ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String findPrefix(String word, Trie root) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (root.children[index] == null) {
                break;
            }
            sb.append(c);
            root = root.children[index];
            if (root.isEnd) return sb.toString();
        }
        return word;
    }

    public void add(Trie root, String word) {
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (root.children[index] == null) {
                root.children[index] = new Trie();
            }
            root = root.children[index];
        }
        root.isEnd = true;
    }


    class Trie {
        Trie[] children = new Trie[26];
        boolean isEnd;
    }

}
