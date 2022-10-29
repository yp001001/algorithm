package test;

import java.net.UnknownHostException;
import java.util.*;

public class Test25 {
    public static void main(String[] args) throws UnknownHostException {
        Test25 test25 = new Test25();
        int speed = test25.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5);
        System.out.println(speed);
    }

    /**
     * 狒狒吃香蕉
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 1;
        int ro = 1_000_000_000;
        int ans = 0;
        while (lo <= ro) {
            int mid = lo + (ro - lo) / 2;
            if (!possible(mid, piles, h)) lo = mid + 1;
            else {
                ans = mid;
                ro = mid - 1;
            }
        }
        return ans;
    }

    private boolean possible(int p, int[] piles, int h) {
        int time = 0;
        for (int pile : piles) {
            time += (pile - 1) / p + 1;
        }
        return time <= h;
    }

    /**
     * 单词替换之使用前缀和
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    Node node;

    public String replaceWords_2(List<String> dictionary, String sentence) {
        // 将所有的字典保存到Node中
        node = new Node();
        // 将数据都保存到前缀树中
        for (String word : dictionary) {
            Node temp = node;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (temp.children[index] == null) {
                    temp.children[index] = new Node();
                }
                if (i == word.length() - 1) {
                    temp.isEnd = true;
                }
                temp = temp.children[index];
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String word : sentence.split(" ")) {
            String res = prefixWord(word);
            if (res == null) sb.append(word + " ");
            else sb.append(res + " ");
        }
        return sb.toString();
    }

    private String prefixWord(String word) {
        StringBuilder sb = new StringBuilder();
        Node temp = node;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (temp.children[index] == null) return null;
            sb.append(ch);
            if (temp.isEnd) return sb.toString();
            temp = temp.children[index];
        }
        return sb.toString();
    }

    static class Node {
        Node[] children;
        boolean isEnd;

        Node() {
            children = new Node[26];
            isEnd = false;
        }
    }


    /**
     * 单词替换
     *
     * @param dictionary
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dictionary, String sentence) {

        Set<String> rootSet = new HashSet<>(dictionary);

        StringBuilder sb = new StringBuilder();
        for (String word : sentence.split("\\s+")) {
            String prefix = "";
            for (int i = 0; i < word.length(); i++) {
                if (rootSet.contains(word.substring(0, i + 1))) {
                    prefix = word.substring(0, i + 1);
                    break;
                }
            }
            if (!"".equals(prefix)) {
                sb.append(prefix + " ");
            } else {
                sb.append(word + " ");
            }
        }
        return sb.toString();
    }


    /**
     * 出现频率最高的k个数字
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        // 默认小顶堆 o[0]保存key，o[1]保存value（次数）
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] res = new int[k];
        int idx = 0;
        while (!queue.isEmpty()) {
            res[idx++] = queue.poll()[0];
        }
        return res;
    }

}
