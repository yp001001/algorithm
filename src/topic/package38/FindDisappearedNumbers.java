package topic.package38;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
public class FindDisappearedNumbers {

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        for (int i = 0; i < nums.length; i++) {
            // 下标循环怼
            walk(nums, i);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                res.add(i + 1);
            }
        }
        return res;
    }

    private static void walk(int[] nums, int i) {
        while (nums[i] != i + 1) {
            int next = nums[i] - 1;
            // 遇到已经正确的数字直接break;
            if (nums[next] == next + 1) break;
            swap(nums, i, next);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
