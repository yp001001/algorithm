package test2;


public class Test03 {
    public static void main(String[] args) {
        System.out.println(Test03.maxSubarraySumCircular(new int[]{1, -2, 3, -2}));
    }

    /**
     * 环形子数组和的最大值
     *
     * @param nums
     * @return
     */
    public static int maxSubarraySumCircular(int[] nums) {
        // 答案在中间，或者答案在头尾，如果答案在两边，中间的值就是最小值
        int min = nums[0], max = nums[0];
        int pre = nums[0], curr;
        int sum = nums[0];
        // 求出第一种情况
        for (int i = 1; i < nums.length; i++) {
            if (pre <= 0) {
                curr = nums[i];
            } else {
                curr = nums[i] + pre;
            }
            max = Math.max(max, curr);
            pre = curr;
        }
        // 求出第二种情况时的最小值
        pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            if (pre <= 0) {
                curr = nums[i] + pre;
            } else {
                curr = nums[i];
            }
            min = Math.min(min, curr);
            pre = curr;
        }
        if (sum == min) return max;
        return Math.max(sum - min, max);
    }


    /**
     * 跳跃游戏（不是累加）实时维护最远距离
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int maxStep = nums[0];  // 维护最大距离
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (maxStep >= n - 1) return true;
            if (maxStep < i) return false;  // 如果当前能走的最大距离小于当前距离，返回false
            maxStep = Math.max(maxStep, nums[i] + i);
        }
        return maxStep >= nums.length - 1;
    }

    /**
     * 跳跃游戏II（最少步数）
     * ***当我们从第一步开始，我们选择能跳的最远距离，其余能被选择的它都能跳到该位置
     *
     * @param nums
     * @return
     */
    public static int jumpFinally(int[] nums) {
        int pos = nums.length - 1;
        int end = 0; // 作为前一个跳动的节点
        int step = 0;
        int maxPos = 0;
        for (int i = 0; i < pos; i++) {
            maxPos = Math.max(maxPos, i + nums[i]);
            if (i == end) {
                end = maxPos;
                step++;
            }
        }
        return step;
    }


    public static int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {  // -1表示跳到该位置，但是该位置是最后一个位置，所以步数不要 + 1
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public int jump_2(int[] nums) {
        int pos = nums.length - 1;
        int step = 0;
        while (pos > 0) {
            for (int i = 0; i < pos; i++) {
                if (nums[i] + i >= pos) {
                    step++;
                    pos = i;
                    break;
                }
            }
        }
        return step;
    }


    /**
     * 删除并获得点数
     * （只是0,1这样的数无法在一起，填充数）
     *
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int[] res = new int[max + 1];
        for (int num : nums) {
            res[num]++;
        }
        // 相当于打家劫舍
        int n = res.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = res[1] * 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + res[i] * i);
        }
        return dp[n];
    }

    private static int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid - 1;
            } else {
                l = mid;
            }
        }
        return l;
    }
}
