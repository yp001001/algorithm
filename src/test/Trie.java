package test;

public class Trie {
    private Trie[] children;
    private boolean isEnd;

    public static void main(String[] args) {
        Trie root = new Trie();
        root.insert("abc");
    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        // 保存26个小写字母
        children = new Trie[26];
        // 是否到达最后
        isEnd = false;
    }

    class Node {
        String content;
        Node left;
        Node right;
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (trie.children[index] == null) {
                trie.children[index] = new Trie();
            }
            trie = trie.children[index];
        }
        isEnd = true;
    }


    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie trie = this;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';
            if (trie.children[index] == null) return null;
            trie = trie.children[index];
        }
        return trie;
    }
}
