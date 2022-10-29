package everyday;

import java.util.*;

// https://leetcode.cn/problems/maximum-swap/
public class Test53 {

    public static void main(String[] args) {
        System.out.println(maximumSwap_3(98368));
    }

    public static int maximumSwap_3(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length;
        int left = n, right = n;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && chars[stack.peekLast()] < chars[i]) {
                int idx = stack.removeLast();
                if (idx < left) {
                    left = idx;
                    right = i;
                }
                if (idx == right) {
                    right = i;
                }
            }
            if (right < n && chars[right] == chars[i]) {
                right = i;
            }
            stack.addLast(i);
        }
        if (left < n) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            return Integer.parseInt(new String(chars));
        }
        return num;
    }

    public static int maximunSwap_2(int num) {
        char[] str = String.valueOf(num).toCharArray();
        int maxNum = num;
        for (int i = 0; i < str.length; i++) {
            for (int j = i + 1; j < str.length; j++) {
                if (str[j] > str[i]) {
                    swap(str, i, j);
                    maxNum = Math.max(maxNum, Integer.parseInt(new String(str)));
                    swap(str, i, j);
                }
            }
        }
        return Integer.parseInt(String.valueOf(str));
    }

    public static int maximumSwap(int num) {
        char[] str = String.valueOf(num).toCharArray();
        TreeMap<Character, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < str.length; i++) {
            List<Integer> list;
            if (map.containsKey(str[i])) {
                list = map.get(str[i]);
                list.add(0, i);
            } else {
                list = new ArrayList<>();
                list.add(i);
            }
            map.put(str[i], list);
        }
        for (int i = 0; i < str.length; i++) {
            if (str[i] < map.lastKey()) {
                swap(str, i, map.get(map.lastKey()).get(0));
                break;
            } else {
                map.get(str[i]).remove(map.get(str[i]).size() - 1);
                if (map.get(str[i]).size() == 0) map.remove(str[i]);
            }
        }
        return Integer.parseInt(String.valueOf(str));
    }

    private static void swap(char[] str, int a, Integer b) {
        char tmp = str[a];
        str[a] = str[b];
        str[b] = tmp;
    }

}
