package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Test28 {

    public static void main(String[] args) {
        Test28 test28 = new Test28();
        List<List<Integer>> lists = test28.kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }


    /**
     * 和最小的 k 个数对
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((x1, x2) -> {
            return nums1[x1[0]] + nums2[x1[1]] - nums1[x2[0]] - nums2[x2[1]];
        });
        int m = nums1.length;
        int n = nums2.length;
        // 将 nums1[i] + nums2[0] 保存到队列中
        for (int i = 0; i < Math.min(m, k); i++) {
            queue.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !queue.isEmpty()) {
            int[] is = queue.poll();
            res.add(Arrays.asList(nums1[is[0]], nums2[is[1]]));
            if (is[1] + 1 < n) {
                queue.offer(new int[]{is[0], is[1] + 1});
            }
        }
        return res;
    }

    /**
     * 字典序排数
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        int number = 1;
        for (int i = 1; i <= n; i++) {
            res.add(number);
            if (number * 10 <= n) {
                number *= 10;
            } else {
                // 到了 9 或者要超过最大数时， / 10
                while (number % 10 == 9 || number + 1 > n) {
                    number /= 10;
                }
                number++;
            }
        }
        return res;
    }


    /**
     * 含有k个元素的组合
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> res;
    List<Integer> list;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        list = new ArrayList<>();
        dfs(candidates, target, 0);
        return res;
    }

    private void dfs(int[] candidates, int target, int start) {
        if (target < 0 || candidates.length == start) return;
        if (target == 0) {
            res.add(new ArrayList(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            target -= candidates[i];
            list.add(candidates[i]);
            dfs(candidates, target, i);       // 继续该下标递归
            list.remove(list.size() - 1);
            target += candidates[i];
            dfs(candidates, target, i + 1);
        }
    }


    /**
     * 三数之和
     * **********第二个数增大，第三个数就要减小
     * **********第三个数从大到小，如果小于，前面的就不需要判断
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
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

}
