package topic.package16;

import java.util.*;

// https://leetcode.cn/problems/word-ladder/
public class LadderLength {

   public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        HashMap<String, List<String>> nexts = getNexts(wordList);

        HashMap<String, Integer> distanceMap = new HashMap<>();
        distanceMap.put(beginWord, 1);
        HashSet<String> set = new HashSet<>();
        set.add(beginWord);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            Integer distance = distanceMap.get(cur);
            for (String next : nexts.get(cur)) {
                if (next.equals(endWord)) return distance + 1;
                if (!set.contains(nexts)) {
                    set.add(next);
                    queue.add(next);
                    distanceMap.put(next, distance + 1);
                }
            }
        }
        return 0;
    }

    private HashMap<String, List<String>> getNexts(List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        HashMap<String, List<String>> map = new HashMap<>();
        for (String word : wordSet) {
            List<String> list = getNext(word.toCharArray(), wordSet);
            map.put(word, list);
        }
        return map;
    }

    private List<String> getNext(char[] word, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < word.length; i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                if (j != word[i]) {
                    char tmp = word[i];
                    word[i] = j;
                    if (wordSet.contains(String.valueOf(word))) {
                        res.add(String.valueOf(word));
                    }
                    word[i] = tmp;
                }
            }
        }
        return res;
    }

}
