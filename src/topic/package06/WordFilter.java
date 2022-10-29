package topic.package06;

import java.util.ArrayList;
import java.util.List;

public class WordFilter {

    Trie prefixTrie;
    Trie suffixTrie;

    public WordFilter(String[] words) {
        prefixTrie = new Trie();
        suffixTrie = new Trie();
        for (int i = 0; i < words.length; i++) {
            prefixTrie.insertNode(words[i], i);
            String reverseStr = new StringBuilder(words[i]).reverse().toString();
            suffixTrie.insertNode(reverseStr, i);
        }
    }

    public int f(String pref, String suffix) {
        suffix = new StringBuilder(suffix).reverse().toString();
        List<Integer> prefixList = prefixTrie.search(pref);
        List<Integer> suffixList = suffixTrie.search(suffix);
        int prefixLen = prefixList.size();
        int suffixLen = suffixList.size();
        if (prefixLen == 0 || suffixLen == 0) return -1;
        int i = prefixLen - 1, j = suffixLen - 1;
        while (i >= 0 && j >= 0) {
            // 这是对象类型
            int prefixIndex = prefixList.get(i);
            int suffixIndex = suffixList.get(j);
            if (prefixIndex == suffixIndex){
                return prefixList.get(i);
            } else if (prefixIndex > suffixIndex) {
                i--;
            } else {
                j--;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        WordFilter wordFilter = new WordFilter(new String[]{"a", "a", "aac"});
        System.out.println(wordFilter.f("", ""));
    }
}

// 使用List来保存下标
class TrieNode {
    List<Integer> index;
    TrieNode[] child;

    public TrieNode() {
        index = new ArrayList<>();
        child = new TrieNode[26];
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    /**
     * 向字典树中插入单词和其索引
     *
     * @param word  单词
     * @param index 索引
     */
    public void insertNode(String word, int index) {
        TrieNode cur = root;
        cur.index.add(index);
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            if (cur.child[aChar - 'a'] == null) {
                cur.child[aChar - 'a'] = new TrieNode();
            }
            cur = cur.child[aChar - 'a'];
            cur.index.add(index);
        }
    }

    /**
     * 查找前缀 若存在返回索引 不存在返回空list
     *
     * @param word 单词
     */
    public List<Integer> search(String word) {
        TrieNode cur = root;
        char[] chars = word.toCharArray();
        for (char aChar : chars) {
            if (cur.child[aChar - 'a'] == null) {
                return new ArrayList<>();
            }
            cur = cur.child[aChar - 'a'];
        }
        return cur.index;
    }
}
