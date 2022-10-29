package test2;

import java.util.*;

public class LeftRightTest {

    public static void main(String[] args) {
        LeftRightTest test = new LeftRightTest();
        int[][] merge = test.merge(new int[][]{{1, 4}, {0, 4}});
        for (int[] ints : merge) {
            System.out.println(Arrays.toString(ints));
        }
    }


    //=============================================整数拆分（记忆化搜索）=====================
    int n;
    Map<Integer, Integer> dict;

    public int integerBreak(int n) {
        if (n == 1) return 1;
        dict = new HashMap<>();
        this.n = n;
        return helper(n, 1);
    }

    private int helper(int remains, int start) {
        if (remains <= 0) return 1;
        if (dict.containsKey(remains)) return dict.get(remains);
        int res = 0;
        for (int i = start; i < n; i++) {
            if (i > remains) continue;
            res = Math.max(res, helper(remains - i, start) * i);
        }
        dict.put(remains, res);
        return res;
    }


    public int[][] findContinuousSequence(int target) {
        Integer left = 1, right = 1;
        int sum = 0;
        List<int[]> res = new ArrayList<>();
        while (right <= (target / 2 + 1)) {
            sum += right;
            while (sum > target) {
                sum -= left;
                left++;
            }
            if (sum == target) {
                int[] temp = new int[right - left + 1];
                int index = 0;
                for (int i = left; i <= right; i++) {
                    temp[index++] = i;
                }
                res.add(temp);
            }
            right++;
        }

        int[][] result = new int[res.size()][];
        int index = 0;
        for (int[] re : res) {
            result[index++] = re;
        }
        return result;
    }


    //===============================约瑟夫环
    public int lastRemaining(int n, int m) {
        return -1;
    }


    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if (n <= 1) return n;
        int l = 0, r = 1;
        while (r < n) {
            if (nums[r] == nums[l]) {
                r++;
            } else {
                l++;
                nums[l] = nums[r];
                r++;
            }
        }
        return l;
    }


    /**
     * 较小的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    /*
    给定⼀个⻓度为 n 的整数数组和⼀个⽬标值 target，寻找能够使条件 nums[i] + nums[j] + nums[k] < target
    成⽴的三元组 i, j, k 个数（0 <= i < j < k < n）。
     */
    public int threeSumSmaller(int[] nums, int target) {
        // 求目标值，可以使用对撞指针
        Arrays.sort(nums);
        int ans = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int k = n - 1;
            for (int j = i + 1; j < n - 1; j++) {
                while (j < k && nums[i] + nums[j] + nums[k] >= target) {
                    k--;
                }
                if (j < k) {
                    ans = Math.max(ans, nums[i] + nums[j] + nums[k]);
                }
            }
        }
        return ans;
    }


    /**
     * 最接近的三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return 0;
        Arrays.sort(nums);
        int delat = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int sum = nums[i] + twoSumClosest(nums, i + 1, target - nums[i]);
            if (Math.abs(delat) > Math.abs(target - sum)) {
                delat = target - sum;
            }
        }
        return target - delat;
    }


    // 对撞指针
    int twoSumClosest(int[] nums, int start, int target) {
        int lo = start, hi = nums.length - 1;
        int delta = Integer.MAX_VALUE;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (Math.abs(delta) > Math.abs(target - sum)) {
                delta = target - sum;
            }
            if (sum < target) {
                lo++;
            } else {
                hi--;
            }
        }
        return target - delta;
    }


    /**
     * 较小的两数之和
     *
     * @param nums
     * @param k
     * @return
     */
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int ans = -1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum >= k) {
                right--;
            } else {
                ans = sum;
                left++;
            }
        }
        return ans;
    }


    /**
     * 田忌赛马（当不能改变数据顺序的时候，我们可以使用容器保存数组，并且保存下标）
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // 给 nums2 降序排序
        PriorityQueue<int[]> maxpq = new PriorityQueue<>((int[] pair1, int[] pair2) -> pair2[1] - pair1[1]);
        for (int i = 0; i < n; i++) {
            maxpq.offer(new int[]{i, nums2[i]});
        }
        // 给 nums1 升序排序
        Arrays.sort(nums1);
        int lo = 0, hi = n - 1;
        int[] ans = new int[n];
        while (!maxpq.isEmpty()) {
            int[] pair = maxpq.poll();
            int i = pair[0], num = pair[1];
            // 如果nums1的最大值大于nums2的最大值，我们直接将其替换，否则我们使用nums1的最小值作为nums2的最大值
            if (nums1[hi] > num) {
                ans[i] = nums1[hi--];
            } else {
                ans[i] = nums1[lo++];
            }
        }
        return ans;
    }


    /**
     * 区间列表的交集
     *
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList == null || firstList.length == 0 || firstList[0].length == 0 || secondList == null || secondList.length == 0 || secondList[0].length == 0) {
            return new int[0][0];
        }
        List<int[]> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length) {
            int a1 = firstList[i][0], a2 = firstList[i][1];
            int b1 = secondList[j][0], b2 = secondList[j][1];
            // 表示不在该区间
            if (!(b1 > a2 || b2 < a1)) {
                res.add(new int[]{Math.max(a1, b1), Math.min(a2, b2)});
            }
            if (b2 > a2) {
                i++;
            } else {
                j++;
            }
        }
        return res.toArray(new int[0][0]);
    }


    /**
     * 删除被覆盖区间（5.1%）
     *
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals_2(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((int[] pair1, int[] pair2) -> {
            return pair1[0] - pair2[0];
        });
        for (int[] interval : intervals) {
            queue.offer(interval);
        }
        // 我们将小区间合并
        int count = 0;
        while (queue.size() > 1) {
            int[] interval1 = queue.poll();
            int a1 = interval1[0], a2 = interval1[1];
            int[] interval2 = queue.poll();
            int b1 = interval2[0], b2 = interval2[1];
            if (a1 >= b1 && a2 <= b2) {
                queue.offer(interval2);
                count++;
            } else if (b1 >= a1 && b2 <= a2) {
                queue.offer(interval1);
                count++;
            } else {
                // 没有交集
                if (b1 > a2) {
                    queue.offer(interval2);
                } else {  // 有交集
                    queue.offer(new int[]{a1, b2});
                }
            }
        }
        return intervals.length - count;
    }


    int removeCoveredIntervals(int[][] intvs) {
        // 按照起点升序排列，起点相同时降序排列
        Arrays.sort(intvs, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // 记录合并区间的起点和终点
        int left = intvs[0][0];
        int right = intvs[0][1];

        int res = 0;
        for (int i = 1; i < intvs.length; i++) {
            int[] intv = intvs[i];
            // 情况一，找到覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                res++;
            }
            // 情况二，找到相交区间，合并
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况三，完全不相交，更新起点和终点
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }

        return intvs.length - res;
    }


    public int[][] merge(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (x1, x2) -> {
            if (x1[0] == x2[0]) {
                return x1[1] - x2[1];
            }
            return x1[0] - x2[0];
        });
        int lo = intervals[0][0], hi = intervals[0][1];
        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int a = intervals[i][0], b = intervals[i][1];
            if (hi >= a) hi = Math.max(hi, b);
            else {
                res.add(new int[]{lo, hi});
                lo = a;
                hi = b;
            }
        }
        res.add(new int[]{lo, hi});
        return res.toArray(new int[0][0]);
    }
}
