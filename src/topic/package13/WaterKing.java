package topic.package13;

import java.util.*;

// 水王问题
public class WaterKing {

    public static void main(String[] args) {
        printWaterKing(new int[]{1, 2, 4, 5, 6, 7, 2, 2, 2, 2, 2});
        printWaterKing(new int[]{1, 2, 3, 3, 4, 5, 6}, 3);
    }

    public static void printWaterKing(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is null or arr.length == 0");
        }
        int cand = 0;
        int HP = 0;
        for (int a : arr) {
            if (HP == 0) {
                HP++;
                cand = a;
            } else if (cand == a) {
                HP++;
            } else {
                HP--;
            }
        }
        if (HP == 0) {
            System.out.println("no such number");
        }
        HP = 0;
        for (int a : arr) {
            HP = cand == a ? ++HP : HP;
            if (HP > arr.length / 2) {
                System.out.println("has such number, the number is " + cand);
                return;
            }
        }
        System.out.println("no such number");
    }


    public static void printWaterKing(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            System.out.println("arr is null or arr.length == 0");
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            } else {
                if (map.size() == k - 1) {
                    allCandsMinusOne(map);
                } else {
                    map.put(a, 1);
                }
            }
        }
        if (map.size() == 0) {
            System.out.println("no such numbers");
            return;
        }
        Set<Integer> set = map.keySet();
        for (int cand : set) {
            int HP = 0;
            for (int a : arr) {
                HP = cand == a ? ++HP : HP;
                if (HP > arr.length / k) {
                    System.out.println("has such number, the number is " + cand);
                    break;
                }
            }
        }
    }

    private static void allCandsMinusOne(Map<Integer, Integer> map) {
        if (map.size() == 0) return;
        List<Integer> removeList = new ArrayList<>();
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() == 1) removeList.add(entry.getKey());
            else map.put(entry.getKey(), entry.getValue() - 1);
        }
        for (Integer key : removeList) {
            map.remove(key);
        }
    }

}
