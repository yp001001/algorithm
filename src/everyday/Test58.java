package everyday;


import java.util.HashMap;
import java.util.Map;

// https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
public class Test58 {

    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{2, 2, 2, 2, 3, 4, 5}, 4));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0 || nums.length < k) return false;
        int target = sum / k;

        return backtrack(nums, 0, 0, 0, target, k);
    }

    static Map<Integer, Boolean> memo = new HashMap<>();

    private static boolean backtrack(int[] nums, int bucket, int used, int start, int target, int k) {
        if (k == 0) return true;
        if (bucket == target) {
            boolean res = backtrack(nums, 0, used, 0, target, k - 1);
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) return memo.get(used);
        for (int i = start; i < nums.length; i++) {
            if (nums[i] + bucket > target) continue;
            if (((used >> i) & 1) == 1) continue;
            used |= (1 << i);
//            bucket += nums[i];
            if (backtrack(nums, bucket + nums[i], used, i + 1, target, k)) {
                return true;
            }
//            bucket -= nums[i];
            // 撤销选择
            used ^= (1 << i);

        }
        return false;
    }
}
