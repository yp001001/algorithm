package topic.package38;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.cn/problems/task-scheduler/
public class LeastInterval {

    public static void main(String[] args) {
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C', 'D', 'D', 'E'};
        System.out.println(leastInterval(tasks, 2));
    }

    public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int total = 0;
        for (char c : tasks) {
            int count = map.getOrDefault(c, 0) + 1;
            map.put(c, count);
            total++;
        }
        int time = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (total > 0) {
            for (char c = 'A'; c <= 'Z'; c++) {
                if (window.containsKey(c) && window.get(c) > 0) continue;
                if (map.containsKey(c) && map.get(c) > 0) {
                    total--;
                    map.put(c, map.get(c) - 1);
                    window.put(c, n + 1);
                    break;
                }
            }
            delWindow(window);
            time++;
        }
        return time;
    }

    private static void delWindow(Map<Character, Integer> window) {
        for (Map.Entry<Character, Integer> entry : window.entrySet()) {
            if (entry.getValue() > 0) {
                window.put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }

}
