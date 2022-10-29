package everyday;

import java.util.*;

/*
+----+--------------------+------------+-------+---------------+-------------+---------+------+---------+--------------------------+
| id | select_type        | table      | type  | possible_keys | key         | key_len | ref  | rows    | Extra                    |
+----+--------------------+------------+-------+---------------+-------------+---------+------+---------+--------------------------+
|  1 | PRIMARY            | tbiguser   | index | idx_address   | idx_address | 768     | NULL | 3904454 | Using where; Using index |
|  2 | DEPENDENT SUBQUERY | tuser1     | ALL   | NULL          | NULL        | NULL    | NULL |      17 | Using where              |
|  3 | DEPENDENT UNION    | tuser2     | ALL   | NULL          | NULL        | NULL    | NULL |      17 | Using where              |
| NULL | UNION RESULT       | <union2,3> | ALL   | NULL          | NULL        | NULL    | NULL |    NULL | Using temporary          |
+----+--------------------+------------+-------+---------------+-------------+---------+------+---------+--------------------------+
+----+-------------+----------+-------+---------------+-------------+---------+------+---------+--------------------------+
| id | select_type | table    | type  | possible_keys | key         | key_len | ref  | rows    | Extra                    |
+----+-------------+----------+-------+---------------+-------------+---------+------+---------+--------------------------+
|  1 | PRIMARY     | tbiguser | index | idx_address   | idx_address | 768     | NULL | 3904454 | Using where; Using index |
|  3 | SUBQUERY    | tuser2   | ALL   | NULL          | NULL        | NULL    | NULL |      17 | NULL                     |
|  2 | SUBQUERY    | tuser1   | ALL   | NULL          | NULL        | NULL    | NULL |      17 | NULL                     |
+----+-------------+----------+-------+---------------+-------------+---------+------+---------+--------------------------+
+----+-------------+-------+------+---------------+-------------+---------+------------------+---------+----------------------------------------------------+
| id | select_type | table | type | possible_keys | key         | key_len | ref              | rows    | Extra                                              |
+----+-------------+-------+------+---------------+-------------+---------+------------------+---------+----------------------------------------------------+
|  1 | SIMPLE      | t1    | ALL  | NULL          | NULL        | NULL    | NULL             |      17 | Using where; Using temporary; Using filesort       |
|  1 | SIMPLE      | t2    | ALL  | NULL          | NULL        | NULL    | NULL             |      17 | Using where; Using join buffer (Block Nested Loop) |
|  1 | SIMPLE      | b     | ref  | idx_address   | idx_address | 768     | test2.t1.address | 1952227 | Using index                                        |
+----+-------------+-------+------+---------------+-------------+---------+------------------+---------+----------------------------------------------------+
+----+-------------+-------+------+---------------+-------------+---------+------------------+---------+----------------------------------------------------+
| id | select_type | table | type | possible_keys | key         | key_len | ref              | rows    | Extra                                              |
+----+-------------+-------+------+---------------+-------------+---------+------------------+---------+----------------------------------------------------+
|  1 | SIMPLE      | t1    | ALL  | NULL          | NULL        | NULL    | NULL             |      17 | Using where; Using temporary                       |
|  1 | SIMPLE      | t2    | ALL  | NULL          | NULL        | NULL    | NULL             |      17 | Using where; Using join buffer (Block Nested Loop) |
|  1 | SIMPLE      | b     | ref  | idx_address   | idx_address | 768     | test2.t1.address | 1952227 | Using index                                        |
+----+-------------+-------+------+---------------+-------------+---------+------------------+---------+----------------------------------------------------+
*/

// https://leetcode.cn/problems/minimum-cost-to-hire-k-workers/
public class Test52 {

    public static void main(String[] args) {
        specialArray(new int[]{3, 6, 7, 7, 0});
    }


    public static int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] >= n) return n;
        for (int i = 1; i < n; i++) {
            if (nums[n - i] >= i && nums[n - i - 1] < i) {
                return i;
            }
        }
        return -1;
    }


    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        return -1;
    }


    public static int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int ans = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num % 2 != 0) continue;
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (count < map.get(num)) {
                ans = num;
                count = map.get(num);
            } else if (count == map.get(num)) {
                ans = Math.min(ans, num);
            }
        }
        return count == 0 ? -1 : ans;
    }

    public int partitionString(String s) {
        Set<Character> set = new HashSet<>();
        int index = 0;
        int count = 0;
        while (index < s.length()) {
            if (set.contains(s.charAt(index))) {
                count++;
                set.clear();
                set.add(s.charAt(index));
            }
            index++;
        }
        if (set.size() > 0) count++;
        return count;
    }

    // 将区间分为最少组数
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int[] interval : intervals) {
            if (!queue.isEmpty() && queue.peek() < interval[0]) {
                queue.poll();
            }
            queue.offer(interval[1]);
        }
        return queue.size();
    }

    // 最长递增子序列II
    public static int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int sub = nums[i] - nums[j];
                if (sub > 0 && sub <= k) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
