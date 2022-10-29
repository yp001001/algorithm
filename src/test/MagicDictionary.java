package test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 神奇的字典
 * 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，
 * 使得所形成的新单词存在于已构建的神奇字典中
 */
public class MagicDictionary {

    public static void main(String[] args) {
        MagicDictionary dictionary = new MagicDictionary();
        // 149ms  暴力28ms，Map<Integer,List<String>> key保存字符串长度，List保存与目标字符串长度相同的所有字符串
        dictionary.buildDict(new String[]{"hello", "leetcode"});
        dictionary.search("hallo");
    }

    /**
     * Initialize your data structure here.
     */
    Map<String, Integer> dict;
    Set<String> set;

    public MagicDictionary() {
        this.dict = new HashMap<>();
        this.set = new HashSet<>();
    }

    /**
     * x*x
     **/
    public void buildDict(String[] dictionary) {
        for (String dt : dictionary) {
            set.add(dt);
            for (int i = 0; i < dt.length(); i++) {
                String key = dt.substring(0, i) + "*" + dt.substring(i + 1, dt.length());
                dict.put(key, dict.getOrDefault(key, 0) + 1);
            }
        }
    }

    public boolean search(String searchWord) {
        for (int i = 0; i < searchWord.length(); i++) {
            String key = searchWord.substring(0, i) + "*" + searchWord.substring(i + 1, searchWord.length());
            int count = dict.getOrDefault(key, 0);
            if (count > 1 || (count == 1 && !set.contains(searchWord))) {
                return true;
            }
        }
        return false;
    }

}

