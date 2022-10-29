package topic.package38;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

// https://leetcode.cn/problems/partition-labels/
public class PartitionLabels {

    public static void main(String[] args) {
        List<Integer> list = partitionLabels("ababcbacadefegdehijhklijqxmmw");
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }

    public static List<Integer> partitionLabels_2(String S) {
        char[] str = S.toCharArray();
        int[] far = new int[26];
        for (int i = 0; i < str.length; i++) {
            far[str[i] - 'a'] = i;
        }
        List<Integer> ans = new ArrayList<>();
        int left = 0;
        int right = far[str[0] - 'a'];
        for (int i = 1; i < str.length; i++) {
            if (i > right) {
                ans.add(right - left + 1);
                left = i;
            }
            right = Math.max(right, far[str[i] - 'a']);
        }
        ans.add(right - left + 1);
        return ans;
    }

    public static List<Integer> partitionLabels(String s) {
        if (s == null || s.length() == 0) return null;
        char[] str = s.toCharArray();
        TreeMap<Character, int[]> map = new TreeMap<>();
        for (int i = 0; i < str.length; i++) {
            int[] arr;
            if (map.containsKey(str[i])) {
                arr = map.get(str[i]);
                arr[1] = i;
            } else {
                arr = new int[]{i, i};
            }
            map.put(str[i], arr);
        }
        int end = map.get(str[0])[1];
        int count = 1;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < str.length; i++) {
            end = Math.max(map.get(str[i])[1], end);
            if (i == end) {
                res.add(count);
                count = 0;
            }
            count++;
        }
        return res;
    }

}
