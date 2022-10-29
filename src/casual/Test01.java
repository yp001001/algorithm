package casual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test01 {

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{1, 2, -2, -1});
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    // https://leetcode.cn/problems/remove-duplicate-letters/
    public static String removeDuplicateLetters(String s) {
        if (s == null || s.length() == 0) return s;
        char[] str = s.toCharArray();
        int n = str.length;
        boolean[] vis = new boolean[26];
        int[] nums = new int[26];
        for (char c : str) {
            nums[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            int index = str[i] - 'a';
            if (!vis[index]) {
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > str[i]) {
                    if (nums[sb.charAt(sb.length() - 1) - 'a'] > 0) {
                        vis[sb.charAt(sb.length() - 1) - 'a'] = false;
                        sb.deleteCharAt(sb.length() - 1);
                    } else {
                        break;
                    }
                }
                vis[index] = true;
                sb.append(str[i]);
            }
            nums[index]--;
        }
        return sb.toString();
    }


    public List<List<Integer>> threeSum_2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                while (nums[i] + nums[j] + nums[k] > 0 && j < k) {
                    k--;
                }
                if (j < k && nums[i] + nums[j] + nums[k] == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }
        return res;
    }


    // https://leetcode.cn/problems/3sum/
    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) return null;
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int k = n - 1;
            for (int j = i + 1; j < n - 1; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int target = nums[i] + nums[j];
                while (k > j && nums[k] + target > 0) {
                    k--;
                }
                if (k > j && nums[k] + target == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                }
            }
        }
        return res;
    }

}
