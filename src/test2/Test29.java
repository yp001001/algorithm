package test2;

import java.util.*;

public class Test29 {

    public static void main(String[] args) {
        Test29 test29 = new Test29();
        int[] ans = test29.topKFrequent(new int[]{5, 3, 1, 1, 1, 3, 73, 1}, 2);
        System.out.println(Arrays.toString(ans));
    }

    Map<Integer, Integer> dict = new HashMap<>();
    int[] preorder;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        int index = 0;
        for (int val : inorder) {
            dict.put(val, index++);
        }
        return recur(0, 0, preorder.length - 1);
    }

    private TreeNode recur(int root, int left, int right) {
        if (left > right) {
            return null;
        }
        // root节点下标
        int rootIndex = dict.get(preorder[root]);
        TreeNode r = new TreeNode(root);
        r.left = recur(root + 1, left, rootIndex - 1);
        r.right = recur(root + rootIndex - left + 1, rootIndex + 1, right);
        return r;
    }


    boolean[] marked;
    List<List<Integer>> res;

    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        marked = new boolean[n];
        Arrays.sort(nums);
        dfs(nums, n, list);
        return res;
    }

    private void dfs(int[] nums, int n, List<Integer> list) {
        if (list.size() == n) {
            res.add(new ArrayList<>(list));
            return;
        }

        // 可以保证不会数组越界
        for (int i = 0; i < n; i++) {
            if (marked[i] || (i > 0 && nums[i] == nums[i - 1] && !marked[i - 1])) continue;
            marked[i] = true;
            list.add(nums[i]);
            dfs(nums, n, list);
            marked[i] = false;
            list.remove(list.size() - 1);
        }
    }


    // 岛屿问题
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    // 避免重复计算，将岛屿标记成2
    public void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0
                || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = 2;
        infect(grid, i + 1, j);
        infect(grid, i - 1, j);
        infect(grid, i, j + 1);
        infect(grid, i, j - 1);
    }


    // 和为k的子数组（范围有负数）
    public int subarraySum_2(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, count = 0;
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    /**
     * 优美子数组
     **/
    public int numberOfSubarrays_2(int[] nums, int k) {
        int left = 0, right = 0, oddCnt = 0, res = 0;
        while (right < nums.length) {
            // 右指针先走，每遇到一个奇数则 oddCnt++。
            if ((nums[right++] & 1) == 1) {
                oddCnt++;
            }

            //  若当前滑动窗口 [left, right) 中有 k 个奇数了，进入此分支统计当前滑动窗口中的优美子数组个数。
            if (oddCnt == k) {
                // 先将滑动窗口的右边界向右拓展，直到遇到下一个奇数（或出界）
                // rightEvenCnt 即为第 k 个奇数右边的偶数的个数
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEvenCnt = right - tmp;
                // leftEvenCnt 即为第 1 个奇数左边的偶数的个数
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    leftEvenCnt++;
                    left++;
                }
                // 第 1 个奇数左边的 leftEvenCnt 个偶数都可以作为优美子数组的起点
                // (因为第1个奇数左边可以1个偶数都不取，所以起点的选择有 leftEvenCnt + 1 种）
                // 第 k 个奇数右边的 rightEvenCnt 个偶数都可以作为优美子数组的终点
                // (因为第k个奇数右边可以1个偶数都不取，所以终点的选择有 rightEvenCnt + 1 种）
                // 所以该滑动窗口中，优美子数组左右起点的选择组合数为 (leftEvenCnt + 1) * (rightEvenCnt + 1)
                res += (leftEvenCnt + 1) * (rightEvenCnt + 1);

                // 此时 left 指向的是第 1 个奇数，因为该区间已经统计完了，因此 left 右移一位，oddCnt--
                left++;
                oddCnt--;
            }

        }

        return res;
    }


    public int numberOfSubarrays(int[] nums, int k) {
        int left = 0, right = 0, count = 0, oddCnt = 0;
        while (right < nums.length) {
            oddCnt += (nums[right++] & 1) == 1 ? 1 : 0;
            if (oddCnt == k) {
                int tmp = right;
                while (right < nums.length && (nums[right] & 1) == 0) {
                    right++;
                }
                int rightEventCnt = right - tmp;
                int leftEvenCnt = 0;
                while ((nums[left] & 1) == 0) {
                    left++;
                    leftEvenCnt++;
                }
                count += (rightEventCnt + 1) * (leftEvenCnt + 1);
                left++;
                oddCnt--;
            }
        }
        return count;
    }

    // 使用前缀和
    public int numberOfSubarrays_3(int[] nums, int k) {
        // 数组 prefixCnt 的下标是前缀和（即当前奇数的个数），值是前缀和的个数
        int[] prefixCnt = new int[nums.length + 1];
        prefixCnt[0] = 1;
        // 遍历原数组，计算当前的前缀和，统计到prefixCnt数组中
        // 并且在 res 中累加上与当前前缀和差值为k的前缀和的个数
        int res = 0, sum = 0;
        for (int num : nums) {
            sum += num & 1;
            prefixCnt[sum]++;
            if (sum >= k) {
                res += prefixCnt[sum - k];
            }
        }
        return res;
    }


    /* 前k个高频元素 */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<int[]> queue = new PriorityQueue<>((x1, x2) -> {
            return x1[1] - x2[1];
        });
        for (int num : nums) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }


        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int[] ans = new int[]{entry.getKey(), entry.getValue()};
            queue.add(ans);
            while (queue.size() > k) {
                queue.poll();
            }
        }

        int[] res = new int[k];
        int index = 0;
        while (!queue.isEmpty()) {
            res[index++] = queue.poll()[0];
        }
        return res;
    }


    // 最少的硬币数目
    public int coinChange_2(int[] coins, int amount) {
        int rows = coins.length;
        long[][] dp = new long[rows + 1][amount + 1];
        Arrays.fill(dp[0], Integer.MAX_VALUE);
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j < coins[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[rows][amount] >= Integer.MAX_VALUE ? -1 : (int) dp[rows][amount];
    }

    public int coinChange(int[] coins, int amount) {
        long[] dp = new long[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int j = 0; j <= amount; j++) {
                if (coins[i] <= j) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }
        return dp[amount] >= Integer.MAX_VALUE ? -1 : (int) dp[amount];
    }
}