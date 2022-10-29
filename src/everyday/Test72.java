package everyday;

import java.util.*;

public class Test72 {

    static class StockSpanner {
        private ArrayDeque<int[]> stack;
        int count = 0;

        public StockSpanner() {
            stack = new ArrayDeque<>();
        }

        public int next(int price) {
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                stack.push(new int[]{price, ++count});
                return count;
            }
            int ans = ++count - stack.peek()[1];
            stack.push(new int[]{price, count});
            return ans;
        }
    }


    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int finalTime = 0;
        for (int time : endTime) {
            finalTime = Math.max(finalTime, time);
        }
        // 表示在i时刻获得的最大零花钱
        int[] dp = new int[finalTime + 1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < endTime.length; i++) {
            List<Integer> list = map.getOrDefault(endTime[i], new ArrayList<Integer>());
            list.add(i);
            map.put(endTime[i], list);
        }
        for (int i = 1; i <= finalTime; i++) {
            dp[i] = dp[i - 1];
            List<Integer> list = map.get(i);
            if (list != null) {
                for (int index : list) {
                    dp[i] = Math.max(dp[i], dp[startTime[index] + profit[index]]);
                }
            }
        }
        return dp[finalTime];
    }


    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    Queue<int[]> queue = new ArrayDeque<int[]>();
                    dfs(i, j, grid, queue);
                    int step = 0;
                    while (!queue.isEmpty()) {
                        int sz = queue.size();
                        for (int k = 0; k < sz; k++) {
                            int[] cell = queue.poll();
                            int x = cell[0], y = cell[1];
                            for (int d = 0; d < 4; d++) {
                                int nx = x + dirs[d][0];
                                int ny = y + dirs[d][1];
                                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                                    if (grid[nx][ny] == 0) {
                                        queue.offer(new int[]{nx, ny});
                                        grid[nx][ny] = -1;
                                    } else if (grid[nx][ny] == 1) {
                                        return step;
                                    }
                                }
                            }
                        }
                        step++;
                    }
                }
            }
        }
        return 0;
    }

    public void dfs(int x, int y, int[][] grid, Queue<int[]> queue) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != 1) {
            return;
        }
        queue.offer(new int[]{x, y});
        grid[x][y] = -1;
        dfs(x - 1, y, grid, queue);
        dfs(x + 1, y, grid, queue);
        dfs(x, y - 1, grid, queue);
        dfs(x, y + 1, grid, queue);
    }

    public static int partitionDisjoint(int[] nums) {
        int n = nums.length;
        int[] minRight = new int[n];
        int[] maxLeft = new int[n];
        minRight[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minRight[i] = Math.min(minRight[i + 1], nums[i]);
        }
        maxLeft[0] = nums[0];
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(nums[i], maxLeft[i - 1]);
        }
        for (int i = 0; i < n - 1; i++) {
            if (maxLeft[i] <= minRight[i + 1]) return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{20, -5, 6}, 21));
    }


    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSumArr = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSumArr[i + 1] = preSumArr[i] + nums[i];
        }
        int res = n + 1;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            long curSum = preSumArr[i];
            while (!queue.isEmpty() && curSum - preSumArr[queue.peekFirst()] >= k) {
                res = Math.min(res, i - queue.pollFirst());
            }
            while (!queue.isEmpty() && preSumArr[queue.peekLast()] >= curSum) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return res < n + 1 ? res : -1;
    }
}
