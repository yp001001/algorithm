package everyday;

import java.util.*;

public class Test03 {

    public static void main(String[] args) {
        System.out.println(distanceBetweenBusStops(new int[]{7, 10, 1, 12, 11, 14, 5, 0}, 2, 7));
    }

    public static int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int[] sum = new int[distance.length];
        sum[0] = distance[0];
        if (start > destination) {
            int temp = start;
            start = destination;
            destination = temp;
        }
        for (int i = 1; i < distance.length; i++) {
            sum[i] = sum[i - 1] + distance[i];
        }
        int p1 = sum[destination] - sum[start] + distance[start] - distance[destination];
        int p2 = sum[distance.length - 1] - p1;
        return Math.min(p1, p2);
    }

    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        List<String> rec = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            rec.add(entry.getKey());
        }
        Collections.sort(rec, new Comparator<String>() {
            public int compare(String word1, String word2) {
                return cnt.get(word1) == cnt.get(word2) ? word1.compareTo(word2) : cnt.get(word2) - cnt.get(word1);
            }
        });
        return rec.subList(0, k);
    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        for (String word : words) {
            cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }
        // 大顶堆
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue() == o2.getValue() ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            heap.offer(entry);
        }
        List<String> ans = new ArrayList<>();
        while (ans.size() < k) {
            ans.add(heap.poll().getKey());
        }
        return ans;
    }
}
