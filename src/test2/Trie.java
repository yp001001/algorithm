package test2;

public class Trie {


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.startsWith("app"));

        System.out.println(trie.search("apple"));
    }


    Trie[] children;
    boolean flag = false;   // 表示是否是一个完整的字符串

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        children = new Trie[26];
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Trie temp = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (temp.children[index] == null) {
                temp.children[index] = new Trie();
            }
            temp = temp.children[index];
            if (i == word.length() - 1) temp.flag = true;
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Trie temp = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (temp.children[index] == null) {
                return false;
            }
            temp = temp.children[index];
            if (i == word.length() - 1) {
                return temp.flag == true;
            }
        }
        return false;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        Trie temp = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (temp.children[index] == null) {
                return false;
            }
            temp = temp.children[index];
        }
        return true;
    }
}