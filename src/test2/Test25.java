package test2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Test25 {

    public static void main(String[] args) {
        Test25 test25 = new Test25();
        System.out.println(test25.integerBreak(10));
    }


    // 数组长度超过一半的值
    public int majorityElement(int[] nums) {
        return recur(nums, 0, nums.length - 1);
    }

    private int recur(int[] nums, int i, int j) {
        if (i == j) {
            return nums[i];
        }
        int mid = (j - i) / 2 + i;
        int left = recur(nums, i, mid);
        int right = recur(nums, mid + 1, j);
        if (left == right) {
            return left;
        }
        int c1 = countInRange(nums, left, i, mid);
        int c2 = countInRange(nums, right, mid + 1, j);
        return c1 > c2 ? left : right;
    }

    private int countInRange(int[] nums, int target, int l, int r) {
        int count = 0;
        for (int i = l; i <= r; i++) {
            if (target == nums[i]) count++;
        }
        return count;
    }

    public int majorityElement_2(int[] nums) {
        int count = 0;
        Integer tag = null;
        for (int num : nums) {
            if (count == 0) tag = num;
            count += (num == tag) ? 1 : -1;
        }
        return tag;
    }


    public int[] constructArr(int[] a) {
        if (a.length == 0) return a;
        if (a.length == 1) return new int[]{1};
        int n = a.length;
        int[] pre = new int[n];
        pre[0] = a[0];
        int[] last = new int[n];
        last[n - 1] = a[n - 1];
        // 从前遍历到后
        for (int i = 1; i < a.length; i++) {
            pre[i] = a[i] * pre[i - 1];
        }
        // 从后遍历到前
        for (int i = n - 2; i >= 0; i--) {
            last[i] = a[i] * last[i + 1];
        }
        int[] result = new int[n];
        result[0] = last[1];
        result[n - 1] = pre[n - 2];
        for (int i = 1; i < n - 1; i++) {
            result[i] = pre[i - 1] * last[i + 1];
        }
        return result;
    }

    /**
     * 滑动窗口最大值
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; i++) {
            // 栈顶元素为最大元素，依次到后面也是递减的
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            // 计算窗口左侧边界
            int left = i - k + 1;
            if (deque.peekFirst() < left) {
                deque.removeFirst();
            }
            if (i + 1 >= k) {
                ans[left] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }


    public int[] maxSlidingWindow_2(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            ans[i] = windowMax(nums, i, k);
        }
        return ans;
    }

    int windowMax(int[] nums, int i, int k) {
        int max = Integer.MIN_VALUE;
        for (int j = i; j <= i + k - 1; j++) {
            max = Math.max(max, nums[j]);
        }
        return max;
    }


    /**
     * 每日温度
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!deque.isEmpty() && temperatures[deque.peekLast()] <= temperatures[i]) {
                deque.removeLast();
            }
            if (!deque.isEmpty()) {
                ans[i] = deque.peekLast() - i;
            } else {
                ans[i] = 0;
            }
            deque.addLast(i);
        }
        return ans;
    }

    //=============================================整数拆分=====================

    private int maxRes = 0;
    private int n;

    public int integerBreak(int n) {
        if (n == 1) return 1;
        this.n = n;
        backtrack(n, new ArrayList<>(), 1);
        return maxRes;
    }

    private void backtrack(int remains, List<Integer> path, int start) {
        if (remains < 0) return;
        if (remains == 0) {
            int count = 1;
            for (Integer i : path) {
                count *= i;
            }
            maxRes = Math.max(maxRes, count);
            return;
        }
        for (int i = start; i < n; i++) {
            if (i > remains) continue;
            path.add(i);
            backtrack(remains - i, path, start);
            path.remove(path.size() - 1);
        }
    }
}
