package everyday;

import java.util.*;

public class Test57 {

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list, (x1, x2) -> {
            int a = map.get(x1), b = map.get(x2);
            return a == b ? x2 - x1 : a - b;
        });
        int[] ans = new int[list.size()];
        int index = 0;
        for (Integer num : list) {
            ans[index++] = num;
        }
        return ans;
    }

}
