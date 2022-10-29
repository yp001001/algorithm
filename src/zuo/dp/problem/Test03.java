package zuo.dp.problem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Test03 {

    public static void main(String[] args) {
        Test03 test03 = new Test03();
        boolean can = test03.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 1);
        System.out.println(can);
    }


    //=======================================滑动窗口最大值============================

    // 单调队列
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[nums.length - k + 1];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.peekLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            while (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.removeFirst();
            }
            if (i >= k - 1) {
                ans[index++] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }


    //=======================================接雨水============================

    public int trap(int[] height) {
        int n = height.length;
        int[] toRight = new int[n];
        int[] toLeft = new int[n];
        int max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(height[i - 1], max);
            toRight[i] = max;
        }
        max = 0;
        for (int i = n - 2; i >= 0; i--) {
            max = Math.max(height[i + 1], max);
            toLeft[i] = max;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int cap = Math.min(toRight[i], toLeft[i]) - height[i];
            res += cap > 0 ? cap : 0;
        }
        return res;
    }

    //=======================================划分为k个相等的子集============================

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        int target = sum / k;
        int used = 0;
        return backtrack(k, 0, nums, 0, used, target);
    }

    Map<Integer, Boolean> memo = new HashMap<>();

    boolean backtrack(int k, int bucket, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            // 装满了当前桶，递归枚举下一个桶的选择
            // 让下一个桶从 nums[0] 开始选择数字
            boolean res = backtrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) {
            // 避免冗余计算
            return memo.get(used);
        }
        for (int i = start; i < nums.length; i++) {
            if (((used >> i) & 1) == 1) { // 判断第i位是否是1
                // nums[i]已经被装入别的桶中
                continue;
            }
            if (nums[i] + bucket > target) {
                continue;
            }
            // 将第 i位 置为1
            used |= 1 << i;
            bucket += nums[i];
            if (backtrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            used ^= 1 << i;
            bucket -= nums[i];
        }
        return false;
    }


}
