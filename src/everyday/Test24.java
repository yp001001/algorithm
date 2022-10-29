package everyday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

// https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to/
public class Test24 {

    public static void main(String[] args) {
        int[] groupSizes = new int[]{3, 3, 3, 3, 3, 1, 3};
        System.out.println(groupThePeople(groupSizes));
    }

    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        if (groupSizes == null || groupSizes.length == 0) return null;
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> list = map.getOrDefault(groupSizes[i], new ArrayList<Integer>());
            list.add(i);
            map.put(groupSizes[i], list);
            if (list.size() == groupSizes[i]) {
                ans.add(new ArrayList<>(list));
                list.clear();
            }
        }
        return ans;
    }

}
